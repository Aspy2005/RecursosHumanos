package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "pqr")
public class PQR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pqr")
    private int idPqr;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    private Medico medico;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "descripcion")
    private String descripcion;

	public PQR() {
		super();
	}

	public PQR(int idPqr, Medico medico, String tipo, String descripcion) {
		super();
		this.idPqr = idPqr;
		this.medico = medico;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public int getIdPqr() {
		return idPqr;
	}

	public void setIdPqr(int idPqr) {
		this.idPqr = idPqr;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

   
}
