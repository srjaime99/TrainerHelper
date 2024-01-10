package com.example.trainerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trainerhelper.MenuActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VerActividadesActivity extends AppCompatActivity {

    private TextView muestraDeEjerciciosTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_actividades);

        /*Spinner deporteSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.deportes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deporteSpinner.setAdapter(adapter);*/

        Spinner deporteSpinner = findViewById(R.id.spinner);
        String[] deportesArray = ManejoDeportes.recuperarDeportes(getResources().openRawResource(R.raw.deportes));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deportesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deporteSpinner.setAdapter(adapter);

        muestraDeEjerciciosTextView = findViewById(R.id.muestraDeEjercicios);

        findViewById(R.id.btnVolver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAMenu();
            }
        });

        findViewById(R.id.btnVerEjercicios).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deporteSeleccionado = deporteSpinner.getSelectedItem().toString();
                mostrarEjercicios(deporteSeleccionado);
            }
        });
    }

    private void volverAMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void mostrarEjercicios(String deporte){
        muestraDeEjerciciosTextView.setText(ManejoEjercicios.listaEnTexto(ManejoEjercicios.filtrarPorDeporte(ManejoEjercicios.leerJson(this), deporte)));
    }
}
