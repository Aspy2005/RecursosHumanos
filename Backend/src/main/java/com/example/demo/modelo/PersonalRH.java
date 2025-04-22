package com.example.demo.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personal_rh")
public class PersonalRH {

    @Id
    @Column(name = "id_rh")
    private int idRh;  // ID es la cédula y se ingresa manualmente

    @Column(name = "nombre_rh")
    private String nombreRh;

    @Column(name = "apellido_rh")
    private String apellidoRh;

    @Column(name = "telefono_rh")
    private String telefonoRh;

    @Column(name = "direccion_rh")
    private String direccionRh;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "edad_rh")
    private int edadRh;

    @Column(name = "rol_rh")
    private String rolRh;

    @Column(name = "turno_rh")
    private String turnoRh;

    @Column(name = "correo_rh")
    private String correoRh;

    @Column(name = "salario_rh")
    private float salarioRh;

    @Column(name = "residencia_rh")
    private String residenciaRh;

    @Column(name = "cargo_rh")
    private String cargoRh;

    public PersonalRH() {}

    public PersonalRH(int idRh, String nombreRh, String apellidoRh, String telefonoRh, String direccionRh,
                      Date fechaIngreso, int edadRh, String rolRh, String turnoRh, String correoRh, float salarioRh,
                      String residenciaRh, String cargoRh) {
        this.idRh = idRh;
        this.nombreRh = nombreRh;
        this.apellidoRh = apellidoRh;
        this.telefonoRh = telefonoRh;
        this.direccionRh = direccionRh;
        this.fechaIngreso = fechaIngreso;
        this.edadRh = edadRh;
        this.rolRh = rolRh;
        this.turnoRh = turnoRh;
        this.correoRh = correoRh;
        this.salarioRh = salarioRh;
        this.residenciaRh = residenciaRh;
        this.cargoRh = cargoRh;
    }

	public int getIdRh() {
		return idRh;
	}

	public void setIdRh(int idRh) {
		this.idRh = idRh;
	}

	public String getNombreRh() {
		return nombreRh;
	}

	public void setNombreRh(String nombreRh) {
		this.nombreRh = nombreRh;
	}

	public String getApellidoRh() {
		return apellidoRh;
	}

	public void setApellidoRh(String apellidoRh) {
		this.apellidoRh = apellidoRh;
	}

	public String getTelefonoRh() {
		return telefonoRh;
	}

	public void setTelefonoRh(String telefonoRh) {
		this.telefonoRh = telefonoRh;
	}

	public String getDireccionRh() {
		return direccionRh;
	}

	public void setDireccionRh(String direccionRh) {
		this.direccionRh = direccionRh;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getEdadRh() {
		return edadRh;
	}

	public void setEdadRh(int edadRh) {
		this.edadRh = edadRh;
	}

	public String getRolRh() {
		return rolRh;
	}

	public void setRolRh(String rolRh) {
		this.rolRh = rolRh;
	}

	public String getTurnoRh() {
		return turnoRh;
	}

	public void setTurnoRh(String turnoRh) {
		this.turnoRh = turnoRh;
	}

	public String getCorreoRh() {
		return correoRh;
	}

	public void setCorreoRh(String correoRh) {
		this.correoRh = correoRh;
	}

	public float getSalarioRh() {
		return salarioRh;
	}

	public void setSalarioRh(float salarioRh) {
		this.salarioRh = salarioRh;
	}

	public String getResidenciaRh() {
		return residenciaRh;
	}

	public void setResidenciaRh(String residenciaRh) {
		this.residenciaRh = residenciaRh;
	}

	public String getCargoRh() {
		return cargoRh;
	}

	public void setCargoRh(String cargoRh) {
		this.cargoRh = cargoRh;
	}

    
}
