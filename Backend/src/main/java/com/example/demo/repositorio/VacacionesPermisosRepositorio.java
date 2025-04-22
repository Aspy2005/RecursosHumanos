package com.example.demo.repositorio;

import com.example.demo.modelo.VacacionesPermisos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VacacionesPermisosRepositorio extends JpaRepository<VacacionesPermisos, Integer> {
	@Query("SELECT v FROM VacacionesPermisos v JOIN FETCH v.medico WHERE v.estado = :estado")
	List<VacacionesPermisos> findByEstadoConMedico(@Param("estado") String estado);
}
