package com.example.trainerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class IncluirEjercicioActivity extends AppCompatActivity {
    TextView mensajeTextView;
    EditText mensajeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_actividad);
        mensajeTextView = findViewById(R.id.DeporTetextView);
        mensajeTextView.setText("Hola Mundo");

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
