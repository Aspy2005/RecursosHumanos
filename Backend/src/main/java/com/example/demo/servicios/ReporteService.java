package com.example.demo.servicios;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ReporteMedicoDTO;
import com.example.demo.modelo.Medico;
import com.example.demo.repositorio.AsistenciaRepositorio;
import com.example.demo.repositorio.EvaluacionRepositorio;
import com.example.demo.repositorio.MedicoRepositorio;
import com.example.demo.repositorio.VacacionesPermisosRepositorio;

@Service
public class ReporteService {

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;

    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;

    @Autowired
    private VacacionesPermisosRepositorio permisosRepositorio;

    public ReporteMedicoDTO generarReporte(String cedula, String tipoReporte) {
        // Convertimos la cédula a int porque es el tipo de idMedico
        int idMedico;
        try {
            idMedico = Integer.parseInt(cedula);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La cédula debe ser un número entero");
        }

        Medico medico = medicoRepositorio.findByIdMedico(idMedico)
                .orElseThrow(() -> new RuntimeException("No se encontró un médico con esa cédula"));

        ReporteMedicoDTO reporte = new ReporteMedicoDTO();

        switch (tipoReporte.toLowerCase()) {
            case "asistencia":
                reporte.setAsistencias(asistenciaRepositorio.findAll().stream()
                        .filter(a -> a.getMedico().getIdMedico() == medico.getIdMedico())
                        .collect(Collectors.toList()));
                break;
            case "desempeno":
                reporte.setEvaluaciones(evaluacionRepositorio.findAll().stream()
                        .filter(e -> e.getMedicoEvaluado().getIdMedico() == medico.getIdMedico())
                        .collect(Collectors.toList()));
                break;
            case "permisos":
                reporte.setPermisos(permisosRepositorio.findAll().stream()
                        .filter(p -> p.getMedico().getIdMedico() == medico.getIdMedico())
                        .collect(Collectors.toList()));
                break;
            default:
                throw new IllegalArgumentException("Tipo de reporte no válido");
        }

        return reporte;
    }
    


    public boolean existeMedicoPorCedula(String cedula) {
        try {
            int id = Integer.parseInt(cedula);
            return medicoRepositorio.existsByIdMedico(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
