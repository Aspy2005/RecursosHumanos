package com.example.demo.servicios;

import com.example.demo.modelo.Medico;
import com.example.demo.repositorio.MedicoRepositorio;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicoServicio {

    @Autowired
    private MedicoRepositorio medicoRepository;

    @Autowired
    private LoginMedicoServicio loginMedicoServicio;

    @Autowired
    private CorreoService correoService; // Inyectamos el servicio de correo

    public List<Medico> obtenerTodosLosMedicos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> obtenerMedicoPorId(int id) {
        return medicoRepository.findById(id);
    }

    public Medico registrarMedico(Medico medico) {
        // Guardamos el nuevo médico
        Medico nuevoMedico = medicoRepository.save(medico);

        // Generamos una contraseña aleatoria
        String passwordGenerada = generarPassword();

        // Creamos el login asociado
        loginMedicoServicio.crearLoginMedico(nuevoMedico, passwordGenerada);

        // Enviamos el correo con las credenciales
        try {
            correoService.enviarCorreoContrasena(nuevoMedico.getCorreoMedico(), String.valueOf(nuevoMedico.getIdMedico()), passwordGenerada);
        } catch (MessagingException e) {
            // Aquí puedes manejar el error, por ejemplo, logueando el problema
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
        // Aquí podrías imprimirla, enviarla por correo, etc.
        System.out.println("Contraseña generada para el médico " + nuevoMedico.getNombreMedico() + ": " + passwordGenerada);

        return nuevoMedico;
    }

    private String generarPassword() {
        return UUID.randomUUID().toString().substring(0, 8); // 8 caracteres aleatorios
    }

    public Medico inactivarMedico(int id) {
        Optional<Medico> optional = medicoRepository.findById(id);
        if (optional.isPresent()) {
            Medico medico = optional.get();
            medico.setEstado("INACTIVO");
            return medicoRepository.save(medico);
        } else {
            throw new RuntimeException("Médico no encontrado");
        }
    }


    public Medico actualizarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }
    
    public List<Medico> obtenerMedicosActivos() {
        return medicoRepository.findByEstado("ACTIVO");
    }

    
    public Medico reactivarMedico(int id) {
        Optional<Medico> optional = medicoRepository.findById(id);
        if (optional.isPresent()) {
            Medico medico = optional.get();
            medico.setEstado("ACTIVO");
            return medicoRepository.save(medico);
        } else {
            throw new RuntimeException("Médico no encontrado");
        }
    }

}
