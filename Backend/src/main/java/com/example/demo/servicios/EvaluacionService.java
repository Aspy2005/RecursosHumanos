package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Medico;
import com.example.demo.repositorio.AsistenciaRepositorio;
import com.example.demo.repositorio.PQRRepositorio;

@Service
public class EvaluacionService {

    @Autowired
    private AsistenciaRepositorio asistenciaRepository;

    @Autowired
    private PQRRepositorio pqrRepository;

    public List<Medico> obtenerMedicosConAsistenciaYPqr() {
        List<Medico> conAsistencia = asistenciaRepository.findMedicosConAsistencia();
        List<Medico> conPqr = pqrRepository.findMedicosConPqr();

        Set<Medico> resultado = new HashSet<>();
        resultado.addAll(conAsistencia);
        resultado.addAll(conPqr);

        return new ArrayList<>(resultado);
    }

}
