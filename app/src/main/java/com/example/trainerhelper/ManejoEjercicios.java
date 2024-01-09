package com.example.trainerhelper;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManejoEjercicios {
    public static List<Ejercicio> leerJson(Context context) {
        StringBuilder jsonContent = new StringBuilder();
        try {
            InputStream ruta = context.getResources().openRawResource(R.raw.ejercicios);

            InputStreamReader inputStreamReader = new InputStreamReader(ruta);
            int data = inputStreamReader.read();

            while (data != -1) {
                char current = (char) data;
                jsonContent.append(current);
                data = inputStreamReader.read();
            }
            ruta.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String cadenaEjercicios = jsonContent.toString();

        Gson gson = new Gson();
        Type tipoLista = new TypeToken<List<Ejercicio>>() {}.getType();
        List<Ejercicio> listaEjercicios = gson.fromJson(cadenaEjercicios, tipoLista);

        return listaEjercicios;
    }

    public static void escribirJson(List<Ejercicio> listaEjercicios, Context context){
        Gson gson = new Gson();
        String json = gson.toJson(listaEjercicios);
        try {
            //File file = new File(context.getFilesDir(), "ejercicios.json");
            //FileWriter fileWriter = new FileWriter(new File(context.getFilesDir(), "ejercicios.json").getAbsolutePath());
            //FileWriter fileWriter = new FileWriter(context.getFilesDir() + "/ejercicios.json");
            FileOutputStream fileOutputStream = context.openFileOutput("ejercicios.json", Context.MODE_PRIVATE);
            fileOutputStream.write(json.getBytes());
            fileOutputStream.close();
            //fileWriter.write(json);
            //fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Ejercicio> filtrarPorDeporte (List<Ejercicio> listaEjercicios, String deporte){
        List<Ejercicio> listaFiltrada = new ArrayList<Ejercicio>();
        for(int i = 0; i < listaEjercicios.size(); i++){
            if(listaEjercicios.get(i).getDeporte().equals(deporte)){
                listaFiltrada.add(listaEjercicios.get(i));
            }
        }
        return listaFiltrada;
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
            listaListas.add(crearSesion(listaEjercicios, deporte,duracionMaxima));
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
        for(int i = 0; i < listaEjercicios.size(); i++){
            texto = texto + "\n" + listaEjercicios.get(i).getNombreEjercicio() + " | " + listaEjercicios.get(i).getDuracion() + "'";
        }
        return texto;
    }
}
