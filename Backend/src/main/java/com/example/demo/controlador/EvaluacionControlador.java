package com.example.demo.controlador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.EvaluacionRequest;
import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Evaluacion;
import com.example.demo.modelo.Medico;
import com.example.demo.modelo.PQR;
import com.example.demo.modelo.PersonalRH;
import com.example.demo.repositorio.AsistenciaRepositorio;
import com.example.demo.repositorio.EvaluacionRepositorio;
import com.example.demo.repositorio.MedicoRepositorio;
import com.example.demo.repositorio.PQRRepositorio;
import com.example.demo.repositorio.PersonalRHRepositorio;
import com.example.demo.servicios.EvaluacionService;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionControlador {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping("/medicos")
    public ResponseEntity<List<Medico>> getMedicosConAsistenciaYPqr() {
        List<Medico> medicos = evaluacionService.obtenerMedicosConAsistenciaYPqr();
        return ResponseEntity.ok(medicos);
    }
    
    @Autowired
    private AsistenciaRepositorio asistenciaRepository;

    @Autowired
    private PQRRepositorio pqrRepository;
    
    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private PersonalRHRepositorio personalRHRepositorio;

    @GetMapping("/evaluacion/detalles")
    public Map<String, Object> obtenerDatosEvaluacion(
            @RequestParam int idMedico,
            @RequestParam int mes,
            @RequestParam int anio) {

        List<Asistencia> asistencias = asistenciaRepository.findByMedicoAndMesAndAnio(idMedico, mes, anio);
        List<PQR> pqrs = pqrRepository.findByMedico(idMedico);

        Map<String, Object> response = new HashMap<>();
        response.put("asistencias", asistencias);
        response.put("pqrs", pqrs);

        return response;
    }
    
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEvaluacion(@RequestBody EvaluacionRequest request) {
        Optional<Medico> medico = medicoRepositorio.findById(request.getIdMedicoEvaluado());
        Optional<PersonalRH> rh = personalRHRepositorio.findById(request.getIdPersonalRH());

        if (!medico.isPresent() || !rh.isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Médico o personal de RH no encontrado.");
            return ResponseEntity.badRequest().body(error);
        }

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setMedicoEvaluado(medico.get());
        evaluacion.setPersonalRH(rh.get());
        evaluacion.setFecha(new Date());
        evaluacion.setPuntajeDesempeno(request.getPuntajeDesempeno());
        evaluacion.setObservaciones(request.getObservaciones());

        evaluacionRepositorio.save(evaluacion);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Evaluación guardada correctamente.");
        return ResponseEntity.ok(respuesta);
    }





}
