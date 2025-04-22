package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "login_rh")
public class LoginRH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generación automática de ID
    @Column(name = "usuario_rh")
    private Integer usuarioRh;  // Se usará Integer y se generará automáticamente en la base de datos

    @Column(name = "password_rh")
    private String passwordRh;

    @ManyToOne
    @JoinColumn(name = "id_rh", referencedColumnName = "id_rh")
    private PersonalRH personalRH;

    // Constructor vacío
    public LoginRH() {}

    // Constructor con parámetros
    public LoginRH(Integer usuarioRh, String passwordRh, PersonalRH personalRH) {
        this.usuarioRh = usuarioRh;
        this.passwordRh = passwordRh;
        this.personalRH = personalRH;
    }

    // Getters y Setters
    public Integer getUsuarioRh() {
        return usuarioRh;
    }

    public void setUsuarioRh(Integer usuarioRh) {
        this.usuarioRh = usuarioRh;
    }

    public String getPasswordRh() {
        return passwordRh;
    }

    public void setPasswordRh(String passwordRh) {
        this.passwordRh = passwordRh;
    }

    public PersonalRH getPersonalRH() {
        return personalRH;
    }

    public void setPersonalRH(PersonalRH personalRH) {
        this.personalRH = personalRH;
    }
}
