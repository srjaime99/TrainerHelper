package com.example.trainerhelper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
                int duracion = 0;
                if(duracionEditText.getText().toString().equals("")){
                    Toast.makeText(CrearSesionActivity.this, "Introduzca una duracion", Toast.LENGTH_SHORT).show();
                }else{
                    duracion = Integer.parseInt(duracionEditText.getText().toString());
                    mostrarSesion(deporteSeleccionado, duracion);
                    findViewById(R.id.btnCopiar).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.btnCopiar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Sesion copiada", sesionCreadaTextView.getText());
                clipboard.setPrimaryClip(clip);
            }
        });
    }

    private void mostrarSesion(String deporteSeleccionado, int duracion){
        sesionCreadaTextView.setText(ManejoEjercicios.listaEnTexto(ManejoEjercicios.crearSesionMejorado(AppData.LISTA_EJERCICIOS, deporteSeleccionado, duracion)));
    }

    private void volverAMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
