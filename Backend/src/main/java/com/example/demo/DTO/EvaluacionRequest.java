package com.example.demo.DTO;

public class EvaluacionRequest {
    private int idMedicoEvaluado;
    private int idPersonalRH;
    private int puntajeDesempeno;
    private String observaciones;

    // Getters y Setters
    public int getIdMedicoEvaluado() {
        return idMedicoEvaluado;
    }

    public void setIdMedicoEvaluado(int idMedicoEvaluado) {
        this.idMedicoEvaluado = idMedicoEvaluado;
    }

    public int getIdPersonalRH() {
        return idPersonalRH;
    }

    public void setIdPersonalRH(int idPersonalRH) {
        this.idPersonalRH = idPersonalRH;
    }

    public int getPuntajeDesempeno() {
        return puntajeDesempeno;
    }

    public void setPuntajeDesempeno(int puntajeDesempeno) {
        this.puntajeDesempeno = puntajeDesempeno;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
