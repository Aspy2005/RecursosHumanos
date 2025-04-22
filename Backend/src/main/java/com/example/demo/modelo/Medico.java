package com.example.demo.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @Column(name = "id_medico")
    private int idMedico;  // ID es la cédula y se ingresa manualmente
    
    @Column(name = "estado")
    private String estado = "ACTIVO";


    @Column(name = "nombre_medico")
    private String nombreMedico;

    @Column(name = "apellido_medico")
    private String apellidoMedico;

    @Column(name = "telefono_medico")
    private String telefonoMedico;

    @Column(name = "direccion_medico")
    private String direccionMedico;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "edad_medico")
    private int edadMedico;

    @Column(name = "rol_medico")
    private String rolMedico;

    @Column(name = "turno_medico")
    private String turnoMedico;

    @Column(name = "correo_medico")
    private String correoMedico;

    @Column(name = "salario_medico")
    private float salarioMedico;

    @Column(name = "residencia_medico")
    private String residenciaMedico;

    @Column(name = "cargo_medico")
    private String cargoMedico;

	public Medico() {
		super();
	}

	public Medico(int idMedico, String estado, String nombreMedico, String apellidoMedico, String telefonoMedico,
			String direccionMedico, Date fechaIngreso, int edadMedico, String rolMedico, String turnoMedico,
			String correoMedico, float salarioMedico, String residenciaMedico, String cargoMedico) {
		super();
		this.idMedico = idMedico;
		this.estado = estado;
		this.nombreMedico = nombreMedico;
		this.apellidoMedico = apellidoMedico;
		this.telefonoMedico = telefonoMedico;
		this.direccionMedico = direccionMedico;
		this.fechaIngreso = fechaIngreso;
		this.edadMedico = edadMedico;
		this.rolMedico = rolMedico;
		this.turnoMedico = turnoMedico;
		this.correoMedico = correoMedico;
		this.salarioMedico = salarioMedico;
		this.residenciaMedico = residenciaMedico;
		this.cargoMedico = cargoMedico;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getApellidoMedico() {
		return apellidoMedico;
	}

	public void setApellidoMedico(String apellidoMedico) {
		this.apellidoMedico = apellidoMedico;
	}

	public String getTelefonoMedico() {
		return telefonoMedico;
	}

	public void setTelefonoMedico(String telefonoMedico) {
		this.telefonoMedico = telefonoMedico;
	}

	public String getDireccionMedico() {
		return direccionMedico;
	}

	public void setDireccionMedico(String direccionMedico) {
		this.direccionMedico = direccionMedico;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getEdadMedico() {
		return edadMedico;
	}

	public void setEdadMedico(int edadMedico) {
		this.edadMedico = edadMedico;
	}

	public String getRolMedico() {
		return rolMedico;
	}

	public void setRolMedico(String rolMedico) {
		this.rolMedico = rolMedico;
	}

	public String getTurnoMedico() {
		return turnoMedico;
	}

	public void setTurnoMedico(String turnoMedico) {
		this.turnoMedico = turnoMedico;
	}

	public String getCorreoMedico() {
		return correoMedico;
	}

	public void setCorreoMedico(String correoMedico) {
		this.correoMedico = correoMedico;
	}

	public float getSalarioMedico() {
		return salarioMedico;
	}

	public void setSalarioMedico(float salarioMedico) {
		this.salarioMedico = salarioMedico;
	}

	public String getResidenciaMedico() {
		return residenciaMedico;
	}

	public void setResidenciaMedico(String residenciaMedico) {
		this.residenciaMedico = residenciaMedico;
	}

	public String getCargoMedico() {
		return cargoMedico;
	}

	public void setCargoMedico(String cargoMedico) {
		this.cargoMedico = cargoMedico;
	}

    
}
