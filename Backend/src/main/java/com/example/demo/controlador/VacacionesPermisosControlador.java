package com.example.demo.controlador;

import com.example.demo.modelo.VacacionesPermisos;
import com.example.demo.servicios.VacacionesPermisosService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
public class VacacionesPermisosControlador {

    @Autowired
    private VacacionesPermisosService service;

    @PostMapping("/solicitar")
    public ResponseEntity<?> crearSolicitud(@RequestBody VacacionesPermisos solicitud) {
        try {
            service.crearSolicitud(solicitud);
            return ResponseEntity.ok(Map.of("mensaje", "Solicitud enviada con éxito, pendiente de aprobación."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Error interno: " + e.getMessage()));
        }
    }


}
