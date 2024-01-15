package com.example.trainerhelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ModoDesarrolladorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_desarrollador);

        EditText contraseniaEditText = findViewById(R.id.contrasenia);

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
                probarContrasenia(contrasenia);
            }
        });
    }

    private void probarContrasenia(String contra){
        //guardarContrasenia(this, "Jamon");
        if(verifyPassword(contra)){
            Toast.makeText(ModoDesarrolladorActivity.this, "Contraseña aceptada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ModoDesarrolladorActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

    private void volverAMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public String leerContrasenias(Context context) {
        String contras = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String yourFilePath = context.getFilesDir() + "/passwords.json";
            File yourFile = new File(yourFilePath);
            if (yourFile != null) {
                InputStream inputStream = new FileInputStream(yourFile);
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    while ((receiveString = bufferedReader.readLine()) != null){
                        stringBuilder.append(receiveString);
                    }
                    inputStream.close();
                    contras = stringBuilder.toString();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Excepcion");
        }
        return contras;
    }
    public void guardarContrasenia(Context context, String contra) {
        try {
            contra = codificarContrasenia(contra);
            String path = context.getFilesDir() + "/passwords.json";
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(contra.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("No se ha podido escribir");
        }
    }

    public String codificarContrasenia(String contra) {
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

    public boolean verifyPassword(String prueba) {
        String inputHash = codificarContrasenia(prueba);
        return inputHash != null && inputHash.equals(leerContrasenias(this));
    }
}
