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

    /*public JSONObject toJson() {
        //Metodo usado para convertir el objeto ejercicio a json
        JSONObject jsonEjercicio = new JSONObject();
        try {
            jsonEjercicio.put("deporte", deporte);
            jsonEjercicio.put("nombre", nombreEjercicio);
            jsonEjercicio.put("duracion", descripcion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonEjercicio;
    }*/

    /*public static Ejercicio fromJson(JSONObject jsonObject) {
        //Metodo usado para recuperar el ejercicio de un json
        try {
            String deporte = jsonObject.getString("deporte");
            String nombre = jsonObject.getString("nombre");
            String descripcion = jsonObject.getString("descripcion");
            String materiales = jsonObject.getString("materiales");
            int duracion = jsonObject.getInt("duracion");
            int min = jsonObject.getInt("min");
            int max = jsonObject.getInt("max");
            return new Ejercicio(deporte, nombre, descripcion, materiales, duracion, min, max);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }*/

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
