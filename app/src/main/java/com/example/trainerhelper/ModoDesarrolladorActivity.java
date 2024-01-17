package com.example.trainerhelper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ModoDesarrolladorActivity extends AppCompatActivity {

    private static final String PREF_NAME = "developer_prefs";
    private static final String KEY_PASSWORD = "developer_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_desarrollador);

        EditText contraseniaEditText = findViewById(R.id.contrasenia);
        EditText importarEditText = findViewById(R.id.datosAimportar);

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAMenu();
            }
        });

        Button btnAcceder = findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contrasenia = contraseniaEditText.getText().toString();
                probarContraseña(contrasenia);
            }
        });

        findViewById(R.id.btnCopiar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Sesion copiada", AppData.enTexto());
                clipboard.setPrimaryClip(clip);
            }
        });

        findViewById(R.id.btnImportar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importarEjercicios(importarEditText.getText().toString());
            }
        });
    }

    private void importarEjercicios(String stringEjercicios){
        int ejErroneos = 0, ejRepes = 0, ejAniadidos = 0;
        List<Ejercicio> listaEjercicios = new ArrayList<>();

        if(!stringEjercicios.equals("")) {
            String[] lines = stringEjercicios.split("\n");
            for (String line : lines) {
                String[] campos = line.split("\\|");

                if(campos.length == 7){
                    if(TextUtils.isDigitsOnly(campos[4].trim()) && TextUtils.isDigitsOnly(campos[5].trim()) && TextUtils.isDigitsOnly(campos[6].trim())){
                        Ejercicio ejercicio = new Ejercicio(campos[0].trim(), campos[1].trim(), campos[2].trim(), campos[3].trim(), Integer.parseInt(campos[4].trim()), Integer.parseInt(campos[5].trim()), Integer.parseInt(campos[6].trim()));
                        listaEjercicios.add(ejercicio);
                    }else{
                        ejErroneos++;
                    }
                }else{
                    ejErroneos++;
                }
            }
        }

        for(int i = 0; i < listaEjercicios.size(); i++){
            switch (listaEjercicios.get(i).validar()){
                case 0:
                    AppData.LISTA_EJERCICIOS.add(listaEjercicios.get(i));
                    ejAniadidos++;
                    break;
                case 1:
                    ejErroneos++;
                    break;
                case 2:
                    ejErroneos++;
                    break;
                case 3:
                    ejRepes++;
                    break;
            }
        }
        AppData.escribirEjercicios(this);
        TextView resultadoImportarEditText = findViewById(R.id.resultadoImportar);
        resultadoImportarEditText.setText("Ejercios que no se han podido importar: " + ejErroneos + "\nEjercicios repetidos: " + ejRepes + "\nEjercicios añadidos: " + ejAniadidos);
    }

    private void volverAMenu() {
        finish();
    }

    public void guardarContraseña(Context context, String contraseña) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_PASSWORD, codificarContraseña(contraseña));
        editor.apply();
    }

    public String codificarContraseña(String contra) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(contra.getBytes());
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Algo ha salido mal con las contraseñas");
            return null;
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private void probarContraseña(String prueba){
        guardarContraseña(this, "Jamon");

        String inputHash = codificarContraseña(prueba);
        SharedPreferences prefs = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String contra = prefs.getString(KEY_PASSWORD, "");
        boolean resultado = inputHash != null && inputHash.equals(contra);

        if(resultado){
            Toast.makeText(ModoDesarrolladorActivity.this, "Contraseña aceptada", Toast.LENGTH_SHORT).show();
            findViewById(R.id.btnAcceder).setVisibility(View.GONE);
            findViewById(R.id.contrasenia).setVisibility(View.GONE);
            findViewById(R.id.btnCopiar).setVisibility(View.VISIBLE);
            findViewById(R.id.datosAimportar).setVisibility(View.VISIBLE);
            findViewById(R.id.btnImportar).setVisibility(View.VISIBLE);
            findViewById(R.id.resultadoImportar).setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(ModoDesarrolladorActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }
}
