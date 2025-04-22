package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "login_medico")
public class LoginMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_medico")
    private Long usuarioMedico;  // Cambié el tipo de String a Long

    @Column(name = "password_medico")
    private String passwordMedico;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    private Medico medico;  // Relación con Medico

    public LoginMedico() {}

    public LoginMedico(Long usuarioMedico, String passwordMedico, Medico medico) {
        this.usuarioMedico = usuarioMedico;
        this.passwordMedico = passwordMedico;
        this.medico = medico;
    }

    // Getters y Setters
    public Long getUsuarioMedico() {
        return usuarioMedico;
    }

    public void setUsuarioMedico(Long usuarioMedico) {
        this.usuarioMedico = usuarioMedico;
    }

    public String getPasswordMedico() {
        return passwordMedico;
    }

    public void setPasswordMedico(String passwordMedico) {
        this.passwordMedico = passwordMedico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
