package com.example.trainerhelper;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppData {
    public static List<Ejercicio> LISTA_EJERCICIOS = new ArrayList<Ejercicio>();

    public static void leerEjercicios(Context context) {
        Gson gson = new Gson();
        String text = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String yourFilePath = context.getFilesDir() + "/ejercicios.json";
            File yourFile = new File(yourFilePath);
            if (yourFile != null) {
                InputStream inputStream = new FileInputStream(yourFile);
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
                    LISTA_EJERCICIOS = listaEjercicios;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Excepcion");
        }
    }

    public static void escribirEjercicios(Context context) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(LISTA_EJERCICIOS);
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

    public static String enTexto(){
        String texto = "";
        if(LISTA_EJERCICIOS != null && LISTA_EJERCICIOS.size() != 0){
            for(int i = 0; i < LISTA_EJERCICIOS.size(); i++){
                texto = texto + LISTA_EJERCICIOS.get(i).enTextoExportar() + "\n";
            }
        }
        return texto;
    }
}
