package com.example.trainerhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BorrarEjercicioActivity extends AppCompatActivity {
    private EditText nombreEjercicioEditText;
    private TextView resultadorBorrarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_actividades);

        nombreEjercicioEditText = findViewById(R.id.nombreEjercicio);
        resultadorBorrarTextView = findViewById(R.id.resultadoBorrar);
        resultadorBorrarTextView.setText("");

        findViewById(R.id.btnVolver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAMenu();
            }
        });

        findViewById(R.id.botonBorrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(BorrarEjercicioActivity.this);
                String nombreEjercicio = nombreEjercicioEditText.getText().toString();
                builder.setMessage("¿Quieres borrar el elemento "+ nombreEjercicio + " de la lista?");
                builder.setTitle("Confirmación");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        borrarEjercicio(nombreEjercicio);
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
    }

    private void volverAMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void borrarEjercicio(String nombreEjercicio){
        if(ManejoEjercicios.borrarEjercicio(nombreEjercicio, this)){
            resultadorBorrarTextView.setText("Ejercicio borrado correctamente");
        }else{
            resultadorBorrarTextView.setText("Ejercicio no encontrado");
        }

    }
}
