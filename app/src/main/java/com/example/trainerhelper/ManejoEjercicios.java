package com.example.trainerhelper;


import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Esta clase es utilizada para tener diferentes funcionalidades que se emplean en distintas partes del programa
public class ManejoEjercicios {
    //Indicando el nombre y deporte, trata de eliminar el ejercicio si lo encuentra y hace return de si lo consigue
    public static boolean borrarEjercicio(String nombreEjercicio, Context context, String deporte){
        if (AppData.LISTA_EJERCICIOS != null && AppData.LISTA_EJERCICIOS.size() > 0) {
            for(int i = 0; i < AppData.LISTA_EJERCICIOS.size(); i++){
                if(AppData.LISTA_EJERCICIOS.get(i).getNombreEjercicio().equals(nombreEjercicio)){
                    if(AppData.LISTA_EJERCICIOS.get(i).getDeporte().equals(deporte)){
                        AppData.LISTA_EJERCICIOS.remove(i);
                        AppData.escribirEjercicios(context);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Recibe una lista de ejercicios y un deporte y retira los ejercicios de otros deportes
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

    //suma la duracion de todos los ejercicios de una lista
    public static int duracionTotalLista (List<Ejercicio> listaEjercicios){
        int duracionTotal = 0;
        for(int i = 0; i < listaEjercicios.size(); i++){
            duracionTotal = duracionTotal + listaEjercicios.get(i).getDuracion();
        }
        return duracionTotal;
    }

    //crea una sesion de entrenamiento con el siguiente metodo
    /*
    1 comrpueba que la duracion total es superior a la indicada
    2.1 si es asi mezcla la lista (para evitar crear siempre la misma sesion) y retira el primer ejercicio
    2.2 si no es asi eso quiere decir que la sesion es valida y por lo tanto la devuelve
    */
    public static List<Ejercicio> crearSesion (List<Ejercicio> listaEjercicios, int duracionMaxima){
        while (duracionTotalLista(listaEjercicios) > duracionMaxima){
            Collections.shuffle(listaEjercicios);
            listaEjercicios.remove(0);
        }
        return listaEjercicios;
    }

    //Este metodo llama 10 veces a crearSesion y se queda con la sesion que tenga la duracion mas proxima a la adecuada
    public static List<Ejercicio> crearSesionMejorado (List<Ejercicio> listaEjercicios, String deporte, int duracionMaxima){//solucion 10 veces mas cutre (literalmente)
        List<List<Ejercicio>> listaListas = new ArrayList<List<Ejercicio>>();
        listaEjercicios = filtrarPorDeporte(listaEjercicios, deporte);
        for (int i = 0; i < 10; i++){
            listaListas.add(crearSesion(listaEjercicios, duracionMaxima));
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

    //Convierte la sesion al texto adecuado para presentarlo, con la fecha y los parametros adecuados
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sesionEnTexto(List<Ejercicio> listaEjercicios){
        String texto = "Sesion " + fecha() + "\n";
        if (listaEjercicios != null && listaEjercicios.size() > 0) {
            for(int i = 0; i < listaEjercicios.size(); i++){
                texto = texto + "\n" + listaEjercicios.get(i).getNombreEjercicio() + " | " + listaEjercicios.get(i).getDuracion() + "'";
            }
            return texto;
        } else return "";
    }

    //Crea un string con la fecha actual
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String fecha(){
        LocalDate currentDate = null;
        currentDate = LocalDate.now();

        int year = 0;
            year = currentDate.getYear();

        int month = 0;
            month = currentDate.getMonthValue();

        int day = 0;
            day = currentDate.getDayOfMonth();

        return (day + "/" + month + "/" + year);
    }
}
