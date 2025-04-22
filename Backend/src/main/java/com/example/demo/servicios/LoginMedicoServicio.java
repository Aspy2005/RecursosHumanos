package com.example.demo.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.LoginMedico;
import com.example.demo.modelo.Medico;
import com.example.demo.repositorio.LoginMedicoRepositorio;

@Service
public class LoginMedicoServicio {

    @Autowired
    private LoginMedicoRepositorio repositorio;

 // LoginMedicoServicio.java
    public LoginMedico buscarPorIdMedico(Long idMedico) {
        return repositorio.findByMedicoIdMedico(idMedico);
    }
    
    public LoginMedico crearLoginMedico(Medico medico, String passwordMedico) {
        LoginMedico loginMedico = new LoginMedico();
        loginMedico.setPasswordMedico(passwordMedico);
        loginMedico.setMedico(medico); // Relacionamos el login con el médico
        return repositorio.save(loginMedico);
    }

}
