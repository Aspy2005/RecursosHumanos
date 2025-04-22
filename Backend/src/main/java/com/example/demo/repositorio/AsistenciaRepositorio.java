package com.example.demo.repositorio;

import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.Optional;

public interface AsistenciaRepositorio extends JpaRepository<Asistencia, Integer> {
    Optional<Asistencia> findByMedicoAndFechaBetween(Medico medico, Date inicio, Date fin);
}
