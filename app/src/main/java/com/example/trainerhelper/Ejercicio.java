package com.example.trainerhelper;

import org.json.JSONException;
import org.json.JSONObject;

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

    public String enTextoExportar(){
        return (this.deporte + " | " + this.nombreEjercicio + " | " + this.descripcion + " | " + this.materiales + " | " + this.duracion + " | " + this.participantesMin+ " | " + this.participantesMax);
    }

    public String enTextoMostrar(){
        return ("Nombre: " + this.nombreEjercicio + "\nMateriales: " + this.materiales + "\nDuracion: " + this.duracion + "\nMinimo de participantes: " + this.participantesMin+ "\nMaximo de participantes: " + this.participantesMax + "\nDescripcion: " + this.descripcion);
    }

    public int validar(){//0 = esta OK, 1 = falta algo de rellenar, 2 = problema con participantes, 3 = ejercicio repetido
        //primero comprobamos que todos los apartados esten rellenos
        if(nombreEjercicio.equals("") || descripcion.equals("") || materiales.equals("") || duracion == -1 || participantesMin == -1 || participantesMax == -1){
            return 1;
        }else if(participantesMin > participantesMax){
            return 2;
        }else if(existeEnLista()){
            return 3;
        }
        return 0;
    }

    public boolean existeEnLista(){
        for(int i = 0; i < AppData.LISTA_EJERCICIOS.size(); i++){
            if(AppData.LISTA_EJERCICIOS.get(i).getNombreEjercicio().equals(nombreEjercicio) && AppData.LISTA_EJERCICIOS.get(i).getDeporte().equals(deporte)){
                return true;
            }
        }
        return false;
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
