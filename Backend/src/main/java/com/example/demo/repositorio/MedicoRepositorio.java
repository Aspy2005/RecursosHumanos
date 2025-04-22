package com.example.demo.repositorio;

import com.example.demo.modelo.Medico;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico, Integer> {
    boolean existsById(Integer id);


}
