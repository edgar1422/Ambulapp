package com.example.prueba_repo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Ingreso extends AppCompatActivity {

    private EditText correo_txt, contrasena_txt;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        correo_txt = (EditText) findViewById(R.id.et_correo);
        contrasena_txt = (EditText) findViewById(R.id.et_contrasena);
        btn_login = (Button) findViewById(R.id.button2);
        btn_register = (Button) findViewById(R.id.button3);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo_str = correo_txt.getText().toString();
                String contrasena_str = contrasena_txt.getText().toString();

                if(correo_str.equals("prueba") && contrasena_str.equals("prueba") ) {
                    Intent i = new Intent(Ingreso.this, ActivityPrincipal.class);
                    startActivity(i);
                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Ingreso.this,Registro_usu.class);
                startActivity(i);
            }
        });

    }
    
}
