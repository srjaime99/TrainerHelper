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

    //Constructor para crear los ejercicios con todos los apartados
    public Ejercicio(String deporte, String nombreEjercicio, String descripcion, String materiales, int duracion, int min, int max) {
        this.deporte = deporte;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcion = descripcion;
        this.materiales = materiales;
        this.duracion = duracion;
        this.participantesMin = min;
        this.participantesMax = max;
    }

    //Usado para realizar pruebas cuando es necesario comprobar como es un ejercicio
    public String enTexto(){
        return ("Deporte: " + this.deporte + "\nNombre: " + this.nombreEjercicio + "\nDescripcion: " + this.descripcion + "\nMateriales: " + this.materiales + "\nDuracion: " + this.duracion + "\nMinimo de participantes: " + this.participantesMin+ "\nMaximo de participantes: " + this.participantesMax);
    }

    //Para cuando se selecciona la opcion de exportar se crea un string de una linea con los parametros separados por |
    public String enTextoExportar(){
        return (this.deporte + " | " + this.nombreEjercicio + " | " + this.descripcion + " | " + this.materiales + " | " + this.duracion + " | " + this.participantesMin+ " | " + this.participantesMax);
    }

    //Version para mostrar el contenido del ejercicio sin el deporte y con la descripcion lo ultimo, usado cuando se ven todos los ejercicios
    public String enTextoMostrar(){
        return ("Nombre: " + this.nombreEjercicio + "\nMateriales: " + this.materiales + "\nDuracion: " + this.duracion + "\nMinimo de participantes: " + this.participantesMin+ "\nMaximo de participantes: " + this.participantesMax + "\nDescripcion: " + this.descripcion);
    }

    //Comprueba que un ejercicio es correcto, y si no es asi indica por que motivo no lo es
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

    //Comprueba que no este un ejercicio con el mismo nombre en el mismo deporte para evitar duplicados
    public boolean existeEnLista(){
        for(int i = 0; i < AppData.LISTA_EJERCICIOS.size(); i++){
            if(AppData.LISTA_EJERCICIOS.get(i).getNombreEjercicio().equals(nombreEjercicio) && AppData.LISTA_EJERCICIOS.get(i).getDeporte().equals(deporte)){
                return true;
            }
        }
        return false;
    }

    // Getters y setters para todos los atributos
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
