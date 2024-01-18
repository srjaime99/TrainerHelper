package com.example.trainerhelper;
//para trabajar con gson
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//para trabajar con los ficheros
import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//para conseguir el tipo de la lista a la hora de extraer los ejercicios
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppData {
    public static List<Ejercicio> LISTA_EJERCICIOS = new ArrayList<Ejercicio>();

    //metodo usado para leer los ejercicios del json y guardarlos en LISTA_EJERCICIOS
    public static void leerEjercicios(Context context) {
        //definimos un nuevo gson
        Gson gson = new Gson();
        //string auxiliar
        String text = "";
        StringBuilder stringBuilder = new StringBuilder();//stringBuilder es un tipo de string modificable sin tener que redefinirlo
        try {
            //cogemos la dirección del archivo.json (en el dispositivo que está ejecutando la aplicación)
            String yourFilePath = context.getFilesDir() + "/ejercicios.json";
            //parseamos la dirección indicada a un objeto File
            File yourFile = new File(yourFilePath);
            if (yourFile != null) {
                //creamos un InputStream
                InputStream inputStream = new FileInputStream(yourFile);
                if (inputStream != null) {
                    //lector del inputStream
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    //búfer del lector
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    //string auxiliar
                    String receiveString = "";
                    //mientras que haya contenido en receiveString y sea el mismo que en búfer:
                    while ((receiveString = bufferedReader.readLine()) != null){
                        //añade el contenido del string que está cogiendo del búfer que está leyendo el archivo al stringBuilder
                        stringBuilder.append(receiveString);
                    }
                    inputStream.close(); //cerramos el archivo
                    text = stringBuilder.toString(); //parseamos el stringBuilder a string
                    /*TypeToken pertenece a la librería gson y le ayuda a representar tipos de datos.
                    * En esta línea, se está obteniendo el tipo de objeto que se está serializando,
                    * que a parte de un JSON es una lista de ejercicios. Así gson sabe qué tipo de objeto debe deserializar*/
                    Type listType = new TypeToken<ArrayList<Ejercicio>>(){}.getType();
                    //se hace la asignación de la List con la que luego trabajaremos
                    List<Ejercicio> listaEjercicios = gson.fromJson(text, listType);
                    LISTA_EJERCICIOS = listaEjercicios;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(context.getString(R.string.error_archivo_no_encontrado));
        } catch (IOException e) {
            System.out.println(context.getString(R.string.error_excepcion));
        }
    }

    //metodo usado para guardar LISTA_EJERCICIOS en ejercicios.json
    public static void escribirEjercicios(Context context) {
        try {
            //declaración de gson
            Gson gson = new Gson();
            //serialziación de datos a JSON
            String json = gson.toJson(LISTA_EJERCICIOS);
            //acceso a la ruta del archivo en el dispositivo
            String path = context.getFilesDir() + "/ejercicios.json";
            //declaramos un archivo en la ruta especificada
            File file = new File(path);
            //creamos objeto para escribir el objeto JSON en el objeto archivo
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(json.getBytes());
            //limpiamos el búfer
            fileOutputStream.flush();
            //cerramos
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println(context.getString(R.string.error_no_se_ha_podido_escribir));
        }
    }

    //metodo usado para transformar LISTA_EJERCICIOS en un string que luego se puede exportar
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
