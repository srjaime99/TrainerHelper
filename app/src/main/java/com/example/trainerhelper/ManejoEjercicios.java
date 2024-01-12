package com.example.trainerhelper;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManejoEjercicios {

    public static List<Ejercicio> leerEjercicios(Context context) {
        Gson gson = new Gson();
        String text = "";
        try {
            String yourFilePath = context.getFilesDir() + "/ejercicios.json";
            File yourFile = new File(yourFilePath);
            InputStream inputStream = new FileInputStream(yourFile);
            StringBuilder stringBuilder = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ((receiveString = bufferedReader.readLine()) != null){
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                text = stringBuilder.toString();
                Type listType = new TypeToken<ArrayList<Ejercicio>>(){}.getType();
                List<Ejercicio> listaEjercicios = gson.fromJson(text, listType);
                return listaEjercicios;
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;

    }

    public static void escribirEjercicios(Context context, List<Ejercicio> listaEjercicios) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(listaEjercicios);
            String path = context.getFilesDir() + "/ejercicios.json";
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(json.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("No se ha podido escribir");
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
