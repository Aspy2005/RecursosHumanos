package com.example.demo.modelo;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private int idAsistencia;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    private Medico medico;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_entrada")
    private Date horaEntrada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_salida")
    private Date horaSalida;

    @Column(name = "horas_trabajadas")
    private float horasTrabajadas;

    public Asistencia() {}

    public Asistencia(int idAsistencia, Medico medico, Date fecha, Date horaEntrada, Date horaSalida, float horasTrabajadas) {
        this.idAsistencia = idAsistencia;
        this.medico = medico;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.horasTrabajadas = horasTrabajadas;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public float getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(float horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
