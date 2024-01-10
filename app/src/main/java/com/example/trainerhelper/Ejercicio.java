package com.example.trainerhelper;


import java.io.Serializable;

public class Ejercicio implements Serializable {
    private String deporte;
    private String nombreEjercicio;
    private String descripcion;
    private String materiales;
    private int duracion;
    private int participantesMin;
    private int participantesMax;

    // Constructor
    public Ejercicio(String deporte, String nombreEjercicio, String descripcion, String materiales, int duracion, int min, int max) {
        this.deporte = deporte;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcion = descripcion;
        this.materiales = materiales;
        this.duracion = duracion;
        this.participantesMin = min;
        this.participantesMax = max;
    }

    public String enTexto(){
        return ("Deporte: " + this.deporte + "\nNombre: " + this.nombreEjercicio + "\nDescripcion: " + this.descripcion + "\nMateriales: " + this.materiales + "\nDuracion: " + this.duracion + "\nMinimo de participantes: " + this.participantesMin+ "\nMaximo de participantes: " + this.participantesMax);
    }



    // Getters y setters para todos los atributos
    //getters
    public String getDeporte() {
        return deporte;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMateriales() {
        return materiales;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getParticipantesMin() {
        return participantesMin;
    }

    public int getParticipantesMax() {
        return participantesMax;
    }
    //setters

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setParticipantesMin(int participantesMin) {
        this.participantesMin = participantesMin;
    }

    public void setParticipantesMax(int participantesMax) {
        this.participantesMax = participantesMax;
    }
}
