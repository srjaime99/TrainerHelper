package com.example.trainerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;


public class IncluirEjercicioActivity extends AppCompatActivity {
    //atributos
    TextView mensajeTextView;
    EditText mensajeEditText;
    String[] prueba = {"Natacion", "Patines", "Yoga", "Tae kwondo"};
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView autoCompleteTxt;
    Spinner spinnerDeportes; //declaramos un spinner que corresponde con el spinner de la vista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_actividad);

        mensajeTextView = findViewById(R.id.DeporTetextView);
        mensajeTextView.setText("Hola Mundo");

        // Inicializar el Spinner
        spinnerDeportes = findViewById(R.id.spinner);
        // Configurar el adaptador del Spinner
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(
                this, R.array.deportes, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDeportes.setAdapter(adapterSpinner);

        findViewById(R.id.btnVolver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {volverAMenu();}
        });
    }

    private void volverAMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
    public void buttonPress(View view){
        Log.i("Info", "Boton presionado");
        mensajeEditText = findViewById(R.id.Material);
        String mensajeString = mensajeEditText.getText().toString();
        mensajeTextView.setText(mensajeString);
    }
}
