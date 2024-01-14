package com.example.trainerhelper;
// Jaime y Manuel
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        AppData.leerEjercicios(this);
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
