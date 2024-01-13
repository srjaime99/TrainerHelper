package com.example.trainerhelper;


import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManejoEjercicios {
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
        String texto = "";
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
                texto = texto + "\n" + listaEjercicios.get(i).getNombreEjercicio() + " | " + listaEjercicios.get(i).getDuracion() + "' | " + listaEjercicios.get(i).getMateriales() + " | " + listaEjercicios.get(i).getParticipantesMin() + " | " + listaEjercicios.get(i).getParticipantesMax();
            }
            return texto;
        } else return "";
    }
}
