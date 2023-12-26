package com.example.trainerhelper;

import org.json.JSONException;
import org.json.JSONObject;

public class Ejercicio {
    private String deporte;
    private String nombreEjercicio;
    private int duracion;

    // Constructor

    public Ejercicio(String deporte, String nombre, int duracion) {
        this.deporte = deporte;
        this.nombreEjercicio = nombre;
        this.duracion = duracion;
    }

    public String enTexto(){
        return ("Deporte: " + this.deporte + ", Nombre: " + this.nombreEjercicio + ", Duracion: " + this.duracion);
    }

    public JSONObject toJson() {
        //Metodo usado para convertir el objeto ejercicio a json
        JSONObject jsonEjercicio = new JSONObject();
        try {
            jsonEjercicio.put("deporte", deporte);
            jsonEjercicio.put("nombre", nombreEjercicio);
            jsonEjercicio.put("duracion", duracion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonEjercicio;
    }

    public static Ejercicio fromJson(JSONObject jsonObject) {
        //Metodo usado para recuperar el ejercicio de un json
        try {
            String deporte = jsonObject.getString("deporte");
            String nombre = jsonObject.getString("nombre");
            int duracion = jsonObject.getInt("duracion");

            return new Ejercicio(deporte, nombre, duracion);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Getters y setters para todos los atributos
    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}
