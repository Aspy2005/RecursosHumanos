package com.example.demo.repositorio;

import com.example.demo.modelo.Medico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico, Integer> {
    boolean existsById(Integer id);
    List<Medico> findByEstado(String estado);



}
