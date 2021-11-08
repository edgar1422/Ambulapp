package com.example.prueba_repo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private EditText correo_txt;
    private EditText contrasena_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_amb);

        //correo_txt = (EditText) findViewById(R.id.et_correo);
        //contrasena_txt = (EditText) findViewById(R.id.et_contraseña);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }

        });


        NavigationView navigationView = findViewById(R.id.navigationview);
        navigationView.setItemIconTintList(null);


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



