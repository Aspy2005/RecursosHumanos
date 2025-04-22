package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.modelo.LoginMedico;

public interface LoginMedicoRepositorio extends JpaRepository<LoginMedico, Long> {  // Cambié el tipo a Long
	// LoginMedicoRepositorio.java
	LoginMedico findByMedicoIdMedico(Long idMedico);  // ← relación con el ID del médico
}
