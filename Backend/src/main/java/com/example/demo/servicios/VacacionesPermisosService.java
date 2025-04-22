package com.example.demo.servicios;

import com.example.demo.modelo.VacacionesPermisos;
import com.example.demo.repositorio.MedicoRepositorio;
import com.example.demo.repositorio.VacacionesPermisosRepositorio;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacacionesPermisosService {

    @Autowired
    private VacacionesPermisosRepositorio repositorio;
    
    @Autowired
    private MedicoRepositorio medicoRepositorio;


    @Autowired
    private CorreoService correoService;

    public VacacionesPermisos crearSolicitud(VacacionesPermisos solicitud) {
        if (solicitud.getFechaFin().before(solicitud.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        // 🔍 Cargar el médico completo desde la base de datos
        int idMedico = solicitud.getMedico().getIdMedico();
        var medico = medicoRepositorio.findById(idMedico)
            .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado con ID: " + idMedico));
        
        solicitud.setMedico(medico); // aseguramos que esté completo

        long diferenciaMs = solicitud.getFechaFin().getTime() - solicitud.getFechaInicio().getTime();
        int dias = (int) ((diferenciaMs / (1000 * 60 * 60 * 24)) + 1);
        solicitud.setDiasSolicitados(dias);
        solicitud.setEstado("Pendiente de Aprobación");

        return repositorio.save(solicitud);
    }


    public List<VacacionesPermisos> obtenerSolicitudesPendientes() {
        return repositorio.findByEstadoConMedico("Pendiente de Aprobación");
    }


    @Transactional
    public void actualizarEstadoSolicitud(int id, String nuevoEstado) {
        Optional<VacacionesPermisos> opt = repositorio.findById(id);
        if (opt.isPresent()) {
            VacacionesPermisos solicitud = opt.get();
            solicitud.setEstado(nuevoEstado);
            repositorio.save(solicitud);

            try {
                String correo = solicitud.getMedico().getCorreoMedico();
                String mensaje = "<p>Hola <b>" + solicitud.getMedico().getNombreMedico() + "</b>,</p>"
                        + "<p>Tu solicitud de <b>" + solicitud.getTipo() + "</b> desde el "
                        + solicitud.getFechaInicio() + " hasta el " + solicitud.getFechaFin()
                        + " ha sido <b>" + nuevoEstado.toUpperCase() + "</b>.</p>";

                correoService.sendEmail(correo, "Actualización de solicitud", mensaje);
            } catch (MessagingException e) {
                System.out.println("Error al enviar correo: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("No se encontró la solicitud con ID: " + id);
        }
    }
}
