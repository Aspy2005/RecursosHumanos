package com.example.demo.servicios;


import com.example.demo.modelo.VacacionesPermisos;
import com.example.demo.repositorio.VacacionesPermisosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class VacacionesPermisosService {

    @Autowired
    private VacacionesPermisosRepositorio repositorio;

    public VacacionesPermisos crearSolicitud(VacacionesPermisos solicitud) {
        // Validaciones
        if (solicitud.getFechaFin().before(solicitud.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        // Calculamos días solicitados (opcional, puedes cambiarlo)
        long diferenciaMs = solicitud.getFechaFin().getTime() - solicitud.getFechaInicio().getTime();
        int dias = (int) ((diferenciaMs / (1000 * 60 * 60 * 24)) + 1); // +1 porque son inclusivos

        solicitud.setDiasSolicitados(dias);
        solicitud.setEstado("Pendiente de Aprobación");

        return repositorio.save(solicitud);
    }
}
