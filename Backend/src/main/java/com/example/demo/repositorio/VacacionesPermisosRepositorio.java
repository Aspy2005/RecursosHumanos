package com.example.demo.repositorio;

import com.example.demo.modelo.VacacionesPermisos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacacionesPermisosRepositorio extends JpaRepository<VacacionesPermisos, Integer> {
    // Podemos agregar métodos personalizados más adelante si hace falta
}
