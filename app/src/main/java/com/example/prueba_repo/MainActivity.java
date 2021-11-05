package com.example.prueba_repo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText correo_txt;
    private EditText contrasena_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        correo_txt = (EditText) findViewById(R.id.et_correo);
        contrasena_txt = (EditText) findViewById(R.id.et_contraseña);



    }

    public void login (View v){

        String correo_str = correo_txt.getText().toString();
        String contrasena_str = correo_txt.getText().toString();

        if (correo_str.equals("prueba")){
            Toast.makeText(this,"Prueba correcta",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Prueba incorrecta",Toast.LENGTH_LONG).show();

        }

    }

}



