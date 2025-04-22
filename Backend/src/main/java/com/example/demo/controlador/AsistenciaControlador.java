package com.example.demo.controlador;

import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Medico;
import com.example.demo.servicios.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaControlador {

    @Autowired
    private AsistenciaService asistenciaService;

    @PostMapping("/entrada")
    public ResponseEntity<?> registrarEntrada(@RequestBody Map<String, Long> request) {
        try {
            Medico medico = new Medico();
            medico.setIdMedico(request.get("idMedico").intValue());
            asistenciaService.registrarEntrada(medico);
            return ResponseEntity.ok(Map.of("mensaje", "Hora de entrada registrada correctamente"));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Error interno: " + e.getMessage()));
        }
    }

    @PostMapping("/salida")
    public ResponseEntity<?> registrarSalida(@RequestBody Map<String, Long> request) {
        try {
            Medico medico = new Medico();
            medico.setIdMedico(request.get("idMedico").intValue());
            asistenciaService.registrarSalida(medico);
            return ResponseEntity.ok(Map.of("mensaje", "Hora de salida registrada correctamente"));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Error interno: " + e.getMessage()));
        }
    }

    @DeleteMapping("/borrar/{idMedico}")
    public ResponseEntity<?> borrarRegistro(@PathVariable Long idMedico) {
        try {
            Medico medico = new Medico();
            medico.setIdMedico(idMedico.intValue());
            asistenciaService.borrarRegistro(medico);
            return ResponseEntity.ok(Map.of("mensaje", "Registro eliminado correctamente"));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Error interno: " + e.getMessage()));
        }
    }


    @GetMapping("/verificar/{idMedico}")
    public ResponseEntity<Map<String, Boolean>> verificar(@PathVariable Long idMedico) {
        Medico medico = new Medico();
        medico.setIdMedico(idMedico.intValue());

        Optional<Asistencia> asistencia = asistenciaService.obtenerAsistenciaPorMedicoYFecha(medico, new Date());

        boolean entrada = asistencia.map(a -> a.getHoraEntrada() != null).orElse(false);
        boolean salida = asistencia.map(a -> a.getHoraSalida() != null).orElse(false);

        return ResponseEntity.ok(Map.of("entradaRegistrada", entrada, "salidaRegistrada", salida));
    }
}
