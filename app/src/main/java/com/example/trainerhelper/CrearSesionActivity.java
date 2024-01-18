package com.example.trainerhelper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
        //manejo del spinner
        Spinner deporteSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.deportes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deporteSpinner.setAdapter(adapter);
        //asociacion de variables
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
                if(duracionEditText.getText().toString().equals("")){
                    Toast.makeText(CrearSesionActivity.this, R.string.introduzca_una_duracion, Toast.LENGTH_SHORT).show();
                }else{
                    int duracion = Integer.parseInt(duracionEditText.getText().toString());
                    mostrarSesion(deporteSeleccionado, duracion);
                    findViewById(R.id.btnCopiar).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.btnCopiar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(getString(R.string.sesion_copiada), sesionCreadaTextView.getText());
                clipboard.setPrimaryClip(clip);
            }
        });
    }

    //recoge el deporte y la duracion maxima, convierte en un string el List que recibe de crearSesionMejordado y lo muestra
    private void mostrarSesion(String deporteSeleccionado, int duracion){
        String sesion = ManejoEjercicios.sesionEnTexto(ManejoEjercicios.crearSesionMejorado(AppData.LISTA_EJERCICIOS, deporteSeleccionado, duracion));
        sesionCreadaTextView.setText(sesion);
    }

    private void volverAMenu() {
        finish();
    }
}
