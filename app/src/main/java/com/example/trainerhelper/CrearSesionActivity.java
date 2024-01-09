package com.example.trainerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CrearSesionActivity extends AppCompatActivity {

    private TextView sesionCreadaTextView;
    private EditText duracionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sesion);

        Spinner deporteSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.deportes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deporteSpinner.setAdapter(adapter);

        sesionCreadaTextView = findViewById(R.id.sesionCreada);

        duracionEditText = findViewById(R.id.seleccionarDuracion);

        findViewById(R.id.btnVolver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAMenu();
            }
        });

        findViewById(R.id.btnCrearSesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deporteSeleccionado = deporteSpinner.getSelectedItem().toString();
                int duracion;
                if(duracionEditText.getText().toString().equals("")){
                    duracion = 0;
                }else{
                    duracion = Integer.parseInt(duracionEditText.getText().toString());
                }
                //String deporteSeleccionado = "Yoga";
                mostrarSesion(deporteSeleccionado, duracion);
            }
        });
    }

    private void mostrarSesion(String deporteSeleccionado, int duracion){
        //sesionCreadaTextView.setText(ManejoEjercicios.listaEnTexto(ManejoEjercicios.crearSesion(ManejoEjercicios.leerJson(this), deporteSeleccionado, duracion)));
        sesionCreadaTextView.setText(ManejoEjercicios.listaEnTexto(ManejoEjercicios.crearSesionMejorado(ManejoEjercicios.leerJson(this), deporteSeleccionado, duracion)));
    }

    private void volverAMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
