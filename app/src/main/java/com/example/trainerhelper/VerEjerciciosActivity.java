package com.example.trainerhelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class VerEjerciciosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ejercicios);

        Spinner deporteSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.deportes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deporteSpinner.setAdapter(adapter);

        // Configurar el botón "Atras"
        Button btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para volver al MenuActivity
                finish();
            }
        });

        // Configurar el botón "Ver ejercicios"
        Button btnVerEjercicios = findViewById(R.id.btnVerEjercicios);
        btnVerEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar los ejercicios al pulsar el botón "Ver ejercicios"
                String deporteSeleccionado = deporteSpinner.getSelectedItem().toString();
                mostrarEjercicios(ManejoEjercicios.filtrarPorDeporte(AppData.LISTA_EJERCICIOS, deporteSeleccionado));
            }
        });
    }

    // Método para mostrar los ejercicios
    private void mostrarEjercicios(List<Ejercicio> listaEjercicios) {
        // Obtener el LinearLayout que contendrá los ejercicios
        LinearLayout layoutEjercicios = findViewById(R.id.layoutEjercicios);

        // Limpiar el layout antes de mostrar los ejercicios
        layoutEjercicios.removeAllViews();

        // Iterar sobre la lista de ejercicios y crear elementos para cada uno
        for (Ejercicio ejercicio : listaEjercicios) {
            // Crear un RelativeLayout para cada ejercicio
            RelativeLayout ejercicioLayout = new RelativeLayout(this);
            ejercicioLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            // Crear TextView para la información del ejercicio
            TextView ejercicioInfo = new TextView(this);
            ejercicioInfo.setText(ejercicio.enTextoMostrar());
            ejercicioInfo.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            ));

            // Crear botón para eliminar el ejercicio
            Button btnEliminar = new Button(this);
            btnEliminar.setText("X");
            btnEliminar.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

            // Configurar las reglas de alineación para centrar el botón en el RelativeLayout
            RelativeLayout.LayoutParams paramsBtnEliminar = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            paramsBtnEliminar.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            paramsBtnEliminar.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
            btnEliminar.setLayoutParams(paramsBtnEliminar);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(VerEjerciciosActivity.this);
                    builder.setMessage("¿Estás seguro de que quieres eliminar el ejercicio " + ejercicio.getNombreEjercicio() + " de la lista?");
                    builder.setTitle("Confirmación");
                    builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lógica para eliminar el ejercicio
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    boolean exito = eliminarEjercicio(ejercicio.getNombreEjercicio());
                                    if (exito) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Actualizar la vista después de eliminar el ejercicio
                                                layoutEjercicios.removeView(ejercicioLayout);
                                                Toast.makeText(VerEjerciciosActivity.this, "Ejercicio eliminado", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(VerEjerciciosActivity.this, "Error al eliminar el ejercicio", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            }).start();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            // Crear la línea divisoria
            View lineaDivisoria = new View(this);
            LinearLayout.LayoutParams paramsLineaDivisoria = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2); // Altura de la línea en píxeles
            lineaDivisoria.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            lineaDivisoria.setLayoutParams(paramsLineaDivisoria);

            // Agregar TextView, Button y línea divisoria al RelativeLayout del ejercicio
            ejercicioLayout.addView(ejercicioInfo);
            ejercicioLayout.addView(btnEliminar);
            ejercicioLayout.addView(lineaDivisoria);

            // Agregar el RelativeLayout del ejercicio al layout principal
            layoutEjercicios.addView(ejercicioLayout);
        }
    }

    private boolean eliminarEjercicio(String nombre){
        return ManejoEjercicios.borrarEjercicio(nombre, this);
    }
}
