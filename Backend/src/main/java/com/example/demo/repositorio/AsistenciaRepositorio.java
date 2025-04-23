package com.example.demo.repositorio;

import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepositorio extends JpaRepository<Asistencia, Integer> {
    Optional<Asistencia> findByMedicoAndFechaBetween(Medico medico, Date inicio, Date fin);
    
    @Query("SELECT DISTINCT a.medico FROM Asistencia a")
    List<Medico> findMedicosConAsistencia();
    
    
    
    
    @Query("SELECT a FROM Asistencia a WHERE a.medico.idMedico = :id AND FUNCTION('MONTH', a.fecha) = :mes AND FUNCTION('YEAR', a.fecha) = :anio")
    List<Asistencia> findByMedicoAndMesAndAnio(@Param("id") int id, @Param("mes") int mes, @Param("anio") int anio);

}