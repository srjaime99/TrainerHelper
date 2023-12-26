package com.example.trainerhelper;

import org.json.JSONException;
import org.json.JSONObject;

public class Ejercicio {
    private String deporte;
    private String nombre;
    private String descripcion;
    private String materiales;
    private int duracion;
    private int minParticipantes;
    private int maxParticipantes;

    // Constructor

    public Ejercicio(String deporte, String nombre, String descripcion, String materiales, int duracion, int minParticipantes, int maxParticipantes) {
        this.deporte = deporte;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.materiales = materiales;
        this.duracion = duracion;
        this.minParticipantes = minParticipantes;
        this.maxParticipantes = maxParticipantes;
    }

    public JSONObject toJson() {
        //Metodo usado para convertir el objeto ejercicio a json
        JSONObject jsonEjercicio = new JSONObject();
        try {
            jsonEjercicio.put("deporte", deporte);
            jsonEjercicio.put("nombre", nombre);
            jsonEjercicio.put("descripcion", descripcion);
            jsonEjercicio.put("materiales", materiales);
            jsonEjercicio.put("duracion", duracion);
            jsonEjercicio.put("minParticipantes", minParticipantes);
            jsonEjercicio.put("maxParticipantes", maxParticipantes);
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
            String descripcion = jsonObject.getString("descripcion");
            String materiales = jsonObject.getString("materiales");
            int duracion = jsonObject.getInt("duracion");
            int minParticipantes = jsonObject.getInt("minParticipantes");
            int maxParticipantes = jsonObject.getInt("maxParticipantes");

            return new Ejercicio(deporte, nombre, descripcion, materiales, duracion, minParticipantes, maxParticipantes);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getMinParticipantes() {
        return minParticipantes;
    }

    public void setMinParticipantes(int minParticipantes) {
        this.minParticipantes = minParticipantes;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }
}
