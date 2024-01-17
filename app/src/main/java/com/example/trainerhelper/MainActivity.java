package com.example.trainerhelper;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Leemos los ejercicios guardados y los almacenamos en AppData.LISTA_EJERCICIOS
        AppData.leerEjercicios(this);
        //Iniciamos el menu
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
