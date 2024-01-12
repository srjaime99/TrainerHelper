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

import java.util.ArrayList;
import java.util.List;

public class IncluirEjercicioActivity extends AppCompatActivity {
    private Ejercicio ejercicio;
    private EditText nombreEjercicioEditText;
    private EditText descripcionEjercicioEditText;
    private EditText duracionEditText;
    private EditText minParticipantesEditText;
    private EditText maxParticipantesEditText;
    private EditText materialesEditText;
    private Spinner deporteSpinner;
    private TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //este metodo determina lo que hace la actividad cuando se inicia
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_actividad); //interfaz de usuario a ejecutar

        // Inicializar las vistas
        nombreEjercicioEditText = findViewById(R.id.nombreEjercicio);
        descripcionEjercicioEditText = findViewById(R.id.descripcionEjercicio);
        duracionEditText = findViewById(R.id.duracion);
        minParticipantesEditText = findViewById(R.id.minimo_duracion);//cambiar nombre a minimo participantes
        maxParticipantesEditText = findViewById(R.id.maximo_duracion);//cambiar nombre a maximo participantes
        materialesEditText = findViewById(R.id.Material);
        deporteSpinner = findViewById(R.id.spinner);
        resultadoTextView = findViewById(R.id.resultado);
        // Cargar los valores del Spinner desde strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.deportes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Configurar el adaptador en el Spinner
        deporteSpinner.setAdapter(adapter);
        // Configurar el botón "Aceptar"
        Button aceptarButton = findViewById(R.id.botonSiguiente);
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para manejar el clic en el botón "Aceptar"
                crearObjetoDesdeEntradasUsuario();
                mostrarValoresDelEjercicioCreado();
            }
        });

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAMenu();
            }
        });
    }

    private void volverAMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    // Método para crear un objeto basado en las entradas del usuario
    private void crearObjetoDesdeEntradasUsuario() {
        // Obtener los valores de las entradas del usuario
        String nombreEjercicio = nombreEjercicioEditText.getText().toString();
        String descripcionEjercicio = descripcionEjercicioEditText.getText().toString();
        int duracion;
        if(duracionEditText.getText().toString().equals("")){
            duracion = 0;
        }else{
            duracion = Integer.parseInt(duracionEditText.getText().toString());
        }
        int participantesMin;
        if(minParticipantesEditText.getText().toString().equals("")){
            participantesMin = 0;
        }else{
            participantesMin = Integer.parseInt(minParticipantesEditText.getText().toString());
        }
        int participantesMax;
        if(maxParticipantesEditText.getText().toString().equals("")){
            participantesMax = 0;
        }else{
            participantesMax = Integer.parseInt(maxParticipantesEditText.getText().toString());
        }
        String materiales = materialesEditText.getText().toString();
        String deporteSeleccionado = deporteSpinner.getSelectedItem().toString();

        // Crear un nuevo objeto Ejercicio con los valores obtenidos
        ejercicio = new Ejercicio(deporteSeleccionado, nombreEjercicio, descripcionEjercicio, materiales, duracion, participantesMin, participantesMax);
        //crear toast con confirmacion
    }
    private void mostrarValoresDelEjercicioCreado() {
        // Comprobar si el ejercicioCreado no es nulo antes de mostrar los valores
        if (ejercicio != null) {
            List<Ejercicio> listaEjercicios = ManejoEjercicios.leerEjercicios(this);
            if (listaEjercicios == null || listaEjercicios.size() < 1) {
                listaEjercicios = new ArrayList<Ejercicio>();
            }
            listaEjercicios.add(ejercicio);
            ManejoEjercicios.escribirEjercicios(this, listaEjercicios);
            resultadoTextView.setText(ejercicio.enTexto());
        } else {
            // Mostrar un mensaje en caso de que no se pueda obtener el ejercicio creado
            resultadoTextView.setText("No se pudo obtener el ejercicio creado.");
        }
    }
}
