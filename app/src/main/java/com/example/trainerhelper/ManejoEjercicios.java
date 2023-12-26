package com.example.trainerhelper;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class ManejoEjercicios {
    public static List<Ejercicio> leerJson(Context context) {
        StringBuilder jsonContent = new StringBuilder();
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.ejercicios);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();

            while (data != -1) {
                char current = (char) data;
                jsonContent.append(current);
                data = inputStreamReader.read();
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String cadenaEjercicios = jsonContent.toString();

        Gson gson = new Gson();
        Type tipoLista = new TypeToken<List<Ejercicio>>() {}.getType();
        List<Ejercicio> listaEjercicios = gson.fromJson(cadenaEjercicios, tipoLista);

        return listaEjercicios;
    }
}
