package com.example.demo.controlador;

import com.example.demo.modelo.Medico;
import com.example.demo.repositorio.MedicoRepositorio;
import com.example.demo.servicios.MedicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicos")
public class MedicoControlador {

    @Autowired
    private MedicoServicio medicoService;
    
    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @GetMapping
    public List<Medico> obtenerTodosLosMedicos() {
        return medicoService.obtenerTodosLosMedicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable int id) {
        Optional<Medico> medico = medicoService.obtenerMedicoPorId(id);
        if (medico.isPresent()) {
            return new ResponseEntity<>(medico.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Medico> registrarMedico(@RequestBody Medico medico) {
        Medico nuevoMedico = medicoService.registrarMedico(medico);
        return new ResponseEntity<>(nuevoMedico, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable int id, @RequestBody Medico medico) {
        medico.setIdMedico(id);  // Asegurarse de que el id es el correcto
        Medico medicoActualizado = medicoService.actualizarMedico(medico);
        return new ResponseEntity<>(medicoActualizado, HttpStatus.OK);
    }

    @PutMapping("/inactivar/{id}")
    public ResponseEntity<?> inactivarMedico(@PathVariable int id) {
        try {
            Medico medicoInactivado = medicoService.inactivarMedico(id);
            return new ResponseEntity<>(medicoInactivado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se pudo inactivar el médico", HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/validar-cedula/{cedula}")
    public ResponseEntity<Boolean> validarCedula(@PathVariable int cedula) {
        boolean existe = medicoRepositorio.existsById(cedula);
        return ResponseEntity.ok(!existe); // true si está disponible, false si ya existe
    }

    @GetMapping("/activos")
    public List<Medico> obtenerMedicosActivos() {
        return medicoService.obtenerMedicosActivos();
    }
    @PutMapping("/reactivar/{id}")
    public ResponseEntity<?> reactivarMedico(@PathVariable int id) {
        try {
            Medico medicoReactivado = medicoService.reactivarMedico(id);
            return new ResponseEntity<>(medicoReactivado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se pudo reactivar el médico", HttpStatus.NOT_FOUND);
        }
    }

    

}
