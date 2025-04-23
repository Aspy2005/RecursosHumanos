package com.example.demo.DTO;

import java.util.List;

import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Evaluacion;
import com.example.demo.modelo.VacacionesPermisos;

public class ReporteMedicoDTO {
    private List<Asistencia> asistencias;
    private List<Evaluacion> evaluaciones;
    private List<VacacionesPermisos> permisos;
	public List<Asistencia> getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}
	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
	public void setEvaluaciones(List<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
	public List<VacacionesPermisos> getPermisos() {
		return permisos;
	}
	public void setPermisos(List<VacacionesPermisos> permisos) {
		this.permisos = permisos;
	}

    // Getters y Setters
}
