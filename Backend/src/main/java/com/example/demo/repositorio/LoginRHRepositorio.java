package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.modelo.LoginRH;

public interface LoginRHRepositorio extends JpaRepository<LoginRH, Long> {  // Cambié el tipo a Long
	// LoginRHRepositorio.java
	LoginRH findByPersonalRHIdRh(Long idRh);  // ← relación con el ID del RH
}
