package com.example.trainerhelper;


import android.content.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManejoEjercicios {

    public static List<Ejercicio> stringToEjercicios(String stringEjercicios){
        String[] lines = stringEjercicios.split("\n");
        List<Ejercicio> ejercicios = new ArrayList<>();
        for (String line : lines) {
            String[] campos = line.split("\\|");

            Ejercicio ejercicio = new Ejercicio(campos[0].trim(), campos[1].trim(), campos[2].trim(), campos[3].trim(), Integer.parseInt(campos[4].trim()), Integer.parseInt(campos[5].trim()), Integer.parseInt(campos[6].trim()));

            ejercicios.add(ejercicio);
        }
        return ejercicios;
    }
    public static boolean borrarEjercicio(String nombreEjercicio, Context context){
        if (AppData.LISTA_EJERCICIOS != null && AppData.LISTA_EJERCICIOS.size() > 0) {
            for(int i = 0; i < AppData.LISTA_EJERCICIOS.size(); i++){
                if(AppData.LISTA_EJERCICIOS.get(i).getNombreEjercicio().equals(nombreEjercicio)){
                    AppData.LISTA_EJERCICIOS.remove(i);
                    AppData.escribirEjercicios(context);
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Ejercicio> filtrarPorDeporte (List<Ejercicio> listaEjercicios, String deporte){
        List<Ejercicio> listaFiltrada = new ArrayList<Ejercicio>();
        if (listaEjercicios != null && listaEjercicios.size() > 0) {
            for(int i = 0; i < listaEjercicios.size(); i++){
                if(listaEjercicios.get(i).getDeporte().equals(deporte)){
                    listaFiltrada.add(listaEjercicios.get(i));
                }
            }
            return listaFiltrada;
        } else {
            return listaEjercicios;
        }
    }

    public static int duracionTotalLista (List<Ejercicio> listaEjercicios){
        int duracionTotal = 0;
        for(int i = 0; i < listaEjercicios.size(); i++){
            duracionTotal = duracionTotal + listaEjercicios.get(i).getDuracion();
        }
        return duracionTotal;
    }

    public static List<Ejercicio> crearSesion (List<Ejercicio> listaEjercicios, String deporte, int duracionMaxima){//solucion cutre
        listaEjercicios = filtrarPorDeporte(listaEjercicios, deporte);
        while (duracionTotalLista(listaEjercicios) > duracionMaxima){
            Collections.shuffle(listaEjercicios);
            listaEjercicios.remove(0);
        }
        return listaEjercicios;
    }

    public static List<Ejercicio> crearSesionMejorado (List<Ejercicio> listaEjercicios, String deporte, int duracionMaxima){//solucion 10 veces mas cutre (literalmente)
        List<List<Ejercicio>> listaListas = new ArrayList<List<Ejercicio>>();
        for (int i = 0; i <  10; i++){
            listaListas.add(crearSesion(listaEjercicios, deporte, duracionMaxima));
        }
        while(listaListas.size() != 1){
            if(duracionTotalLista(listaListas.get(0)) < duracionTotalLista(listaListas.get(1))){
                listaListas.remove(0);
            }else{
                listaListas.remove(1);
            }
        }
        return listaListas.get(0);
    }

    public static String listaEnTexto (List<Ejercicio> listaEjercicios){
        String texto = "Sesion " + fecha() + "\n";
        if (listaEjercicios != null && listaEjercicios.size() > 0) {
            for(int i = 0; i < listaEjercicios.size(); i++){
                texto = texto + "\n" + listaEjercicios.get(i).getNombreEjercicio() + " | " + listaEjercicios.get(i).getDuracion() + "'";
            }
            return texto;
        } else return "";
    }

    public static String listaEnTextoEntero (List<Ejercicio> listaEjercicios){
        String texto = "";
        if (listaEjercicios != null && listaEjercicios.size() > 0) {
            for(int i = 0; i < listaEjercicios.size(); i++){
                texto = texto + listaEjercicios.get(i).getNombreEjercicio() + " | " + listaEjercicios.get(i).getDuracion() + "' | " + listaEjercicios.get(i).getMateriales() + " | " + listaEjercicios.get(i).getParticipantesMin() + " | " + listaEjercicios.get(i).getParticipantesMax() + "\n\n";
            }
            return texto;
        } else return "";
    }

    public static String fecha(){//Si no esta cada cosa rodeado en eso lo marca como error aunque funciona bien
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        int year = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            year = currentDate.getYear();
        }
        int month = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            month = currentDate.getMonthValue();
        }
        int day = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            day = currentDate.getDayOfMonth();
        }

        return (day + "/" + month + "/" + year);
    }
}
