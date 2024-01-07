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
}
