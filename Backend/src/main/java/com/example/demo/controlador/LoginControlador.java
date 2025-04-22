package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modelo.LoginMedico;
import com.example.demo.modelo.LoginRH;
import com.example.demo.servicios.LoginMedicoServicio;
import com.example.demo.servicios.LoginRHServicio;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")

public class LoginControlador {

    @Autowired
    private LoginMedicoServicio loginMedicoServicio;

    @Autowired
    private LoginRHServicio loginRHServicio;

    @PostMapping
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        String usuario = request.get("usuario");
        String contrasena = request.get("contrasena");

        Map<String, Object> response = new HashMap<>();

        try {
            Long id = Long.parseLong(usuario);  // Ahora este "usuario" es el ID del médico o del RH

            // Buscar por ID del médico
            LoginMedico loginMedico = loginMedicoServicio.buscarPorIdMedico(id);
            if (loginMedico != null && loginMedico.getPasswordMedico().equals(contrasena)) {
                response.put("autenticado", true);
                response.put("rol", "medico");
                response.put("id", loginMedico.getMedico().getIdMedico());
                return response;
            }

            // Buscar por ID del RH
            LoginRH loginRH = loginRHServicio.buscarPorIdRh(id);
            if (loginRH != null && loginRH.getPasswordRh().equals(contrasena)) {
                response.put("autenticado", true);
                response.put("rol", "rh");
                response.put("id", loginRH.getPersonalRH().getIdRh());
                return response;
            }

        } catch (NumberFormatException e) {
            response.put("autenticado", false);
            response.put("mensaje", "El formato del usuario es incorrecto");
            return response;
        }

        response.put("autenticado", false);
        response.put("mensaje", "Usuario o contraseña incorrectos");
        return response;
    }

    

}
