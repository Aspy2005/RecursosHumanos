package com.example.demo.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.LoginRH;
import com.example.demo.repositorio.LoginRHRepositorio;

@Service
public class LoginRHServicio {

    @Autowired
    private LoginRHRepositorio repositorio;

 // LoginRHServicio.java
    public LoginRH buscarPorIdRh(Long idRh) {
        return repositorio.findByPersonalRHIdRh(idRh);
    }

}
