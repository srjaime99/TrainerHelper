package com.example.trainerhelper;

import static com.example.trainerhelper.ManejoEjercicios.leerJson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;


public class IncluirEjercicioActivity extends AppCompatActivity {
    TextView mensajeTextView;
    EditText mensajeEditText;
    String[] prueba = {"Natacion", "Patines", "Yoga", "Tae kwondo"};
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView autoCompleteTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_actividad);
        mensajeTextView = findViewById(R.id.DeporTetextView);
        mensajeTextView.setText("Hola Mundo");
        autoCompleteTxt = findViewById(R.id.desplegable);
        autoCompleteTxt.setAdapter(adapterItems);

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
        mensajeEditText = findViewById(R.id.MensajeEditText);
        String mensajeString = mensajeEditText.getText().toString();
        mensajeTextView.setText(mensajeString);
    }
}
