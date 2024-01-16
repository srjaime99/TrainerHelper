package com.example.trainerhelper;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) { //este metodo determina lo que hace la actividad cuando se inicia
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_actividad); //interfaz de usuario a ejecutar

        // Inicializar las vistas
        nombreEjercicioEditText = findViewById(R.id.nombreEjercicio);
        descripcionEjercicioEditText = findViewById(R.id.descripcionEjercicio);
        duracionEditText = findViewById(R.id.duracion);
        minParticipantesEditText = findViewById(R.id.minimo_personas);//cambiar nombre a minimo participantes
        maxParticipantesEditText = findViewById(R.id.maximo_personas);//cambiar nombre a maximo participantes
        materialesEditText = findViewById(R.id.Material);
        deporteSpinner = findViewById(R.id.spinner);
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
        finish();
    }

    // Método para crear un objeto basado en las entradas del usuario
    private void crearObjetoDesdeEntradasUsuario() {
        // Obtener los valores de las entradas del usuario
        String nombreEjercicio = nombreEjercicioEditText.getText().toString();
        String descripcionEjercicio = descripcionEjercicioEditText.getText().toString();
        int duracion;
        if(duracionEditText.getText().toString().equals("")){
            duracion = -1;
        }else{
            duracion = Integer.parseInt(duracionEditText.getText().toString());
        }
        int participantesMin;
        if(minParticipantesEditText.getText().toString().equals("")){
            participantesMin = -1;
        }else{
            participantesMin = Integer.parseInt(minParticipantesEditText.getText().toString());
        }
        int participantesMax;
        if(maxParticipantesEditText.getText().toString().equals("")){
            participantesMax = -1;
        }else{
            participantesMax = Integer.parseInt(maxParticipantesEditText.getText().toString());
        }
        String materiales = materialesEditText.getText().toString();
        String deporteSeleccionado = deporteSpinner.getSelectedItem().toString();

        ejercicio = new Ejercicio(deporteSeleccionado, nombreEjercicio, descripcionEjercicio, materiales, duracion, participantesMin, participantesMax);

        switch (ejercicio.validar()){
            case 1:
                Toast.makeText(IncluirEjercicioActivity.this, "Rellena todos los apartados", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(IncluirEjercicioActivity.this, "El numero de participantes es erroneo", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                Toast.makeText(IncluirEjercicioActivity.this, "Ejercicio repetido", Toast.LENGTH_SHORT).show();
                break;

            case 0:
                if (ejercicio != null) {
                    if (AppData.LISTA_EJERCICIOS == null || AppData.LISTA_EJERCICIOS.size() < 1) {
                        AppData.LISTA_EJERCICIOS = new ArrayList<Ejercicio>();
                    }
                    AppData.LISTA_EJERCICIOS.add(ejercicio);
                    AppData.escribirEjercicios(this);
                    Toast.makeText(IncluirEjercicioActivity.this, "Se ha creado el ejercicio", Toast.LENGTH_SHORT).show();
                    volverAMenu();
                } else {
                    System.out.println("No se pudo añadir el ejercicio correctamente");
                }
                break;

            default:
                System.out.println("Error en la validacion");
        }
    }
}
