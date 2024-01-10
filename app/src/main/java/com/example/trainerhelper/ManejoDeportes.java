package com.example.trainerhelper;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ManejoDeportes {

    public static String[] recuperarDeportes(InputStream inputStream){
        ArrayList<String> listaDeportes = new ArrayList<>();

        try {//lee el archivo deportes.txt y lo convierte en la lista que utiliza el string
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                listaDeportes.add(linea);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaDeportes.toArray(new String[0]);
    }

    public static void agregarDeporte(Context context, String nuevoDeporte) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("deportes.txt", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            outputStreamWriter.write(nuevoDeporte + "\n");

            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
