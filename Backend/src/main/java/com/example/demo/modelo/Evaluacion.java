package com.example.demo.modelo;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluacion")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion")
    private int idEvaluacion;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    private Medico medicoEvaluado;

    @ManyToOne
    @JoinColumn(name = "id_rh", referencedColumnName = "id_rh")
    private PersonalRH personalRH;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "puntaje_desempeno")
    private float puntajeDesempeno;

    @Column(name = "observaciones")
    private String observaciones;

	public Evaluacion() {
		super();
	}

	public Evaluacion(int idEvaluacion, Medico medicoEvaluado, PersonalRH personalRH, Date fecha,
			float puntajeDesempeno, String observaciones) {
		super();
		this.idEvaluacion = idEvaluacion;
		this.medicoEvaluado = medicoEvaluado;
		this.personalRH = personalRH;
		this.fecha = fecha;
		this.puntajeDesempeno = puntajeDesempeno;
		this.observaciones = observaciones;
	}

	public int getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(int idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public Medico getMedicoEvaluado() {
		return medicoEvaluado;
	}

	public void setMedicoEvaluado(Medico medicoEvaluado) {
		this.medicoEvaluado = medicoEvaluado;
	}

	public PersonalRH getPersonalRH() {
		return personalRH;
	}

	public void setPersonalRH(PersonalRH personalRH) {
		this.personalRH = personalRH;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPuntajeDesempeno() {
		return puntajeDesempeno;
	}

	public void setPuntajeDesempeno(float puntajeDesempeno) {
		this.puntajeDesempeno = puntajeDesempeno;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
    
    

}
