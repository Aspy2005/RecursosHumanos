	package com.example.demo.modelo;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "vacaciones_permisos")
public class VacacionesPermisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private int idPermiso;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    private Medico medico;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "dias_solicitados")
    private int diasSolicitados;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "estado")
    private String estado;

	public VacacionesPermisos() {
		super();
	}

	public VacacionesPermisos(int idPermiso, Medico medico, String tipo, int diasSolicitados, Date fechaInicio,
			Date fechaFin, String motivo, String observaciones, String estado) {
		super();
		this.idPermiso = idPermiso;
		this.medico = medico;
		this.tipo = tipo;
		this.diasSolicitados = diasSolicitados;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.motivo = motivo;
		this.observaciones = observaciones;
		this.estado = estado;
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getDiasSolicitados() {
		return diasSolicitados;
	}

	public void setDiasSolicitados(int diasSolicitados) {
		this.diasSolicitados = diasSolicitados;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

    
}
