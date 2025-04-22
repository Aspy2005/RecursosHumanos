package com.example.demo.controlador;

import com.example.demo.modelo.Medico;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable int id) {
        medicoService.eliminarMedico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
