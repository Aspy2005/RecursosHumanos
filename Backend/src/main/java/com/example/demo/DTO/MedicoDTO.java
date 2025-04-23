package com.example.demo.DTO;

public class MedicoDTO {
    private int idMedico;
    private String nombreMedico;
    private String apellido;

    public MedicoDTO(int idMedico, String nombre, String apellido) {
        this.idMedico = idMedico;
        this.nombreMedico = nombre;
        this.apellido = apellido;
    }

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getNombre() {
		return nombreMedico;
	}

	public void setNombre(String nombre) {
		this.nombreMedico = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

    
}