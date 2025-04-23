package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.PersonalRH;

@Repository

public interface PersonalRHRepositorio extends JpaRepository<PersonalRH, Integer>{

}

