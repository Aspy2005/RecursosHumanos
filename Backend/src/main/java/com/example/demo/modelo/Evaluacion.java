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

    // Getters y setters
}
