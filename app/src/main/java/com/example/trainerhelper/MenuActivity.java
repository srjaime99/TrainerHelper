package com.example.trainerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.btnAnadirActividad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, IncluirEjercicioActivity.class));
            }
        });

        findViewById(R.id.btnCrearSesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, CrearSesionActivity.class));
            }
        });

        findViewById(R.id.btnVerActividades).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MenuActivity.this, VerActividadesActivity.class));
                startActivity(new Intent(MenuActivity.this, VerEjerciciosActivity.class));
            }
        });

        findViewById(R.id.btnAccederDev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, ModoDesarrolladorActivity.class));
            }
        });
    }
}

