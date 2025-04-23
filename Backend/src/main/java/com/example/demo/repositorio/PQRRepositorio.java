package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.Medico;
import com.example.demo.modelo.PQR;

public interface PQRRepositorio extends JpaRepository<PQR, Integer> {
    @Query("SELECT DISTINCT p.medico FROM PQR p")
    List<Medico> findMedicosConPqr();
    
    @Query("SELECT p FROM PQR p WHERE p.medico.idMedico = :id")
    List<PQR> findByMedico(@Param("id") int id);

}
