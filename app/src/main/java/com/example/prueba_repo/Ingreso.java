package com.example.prueba_repo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

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
        check_Permission();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo_str = correo_txt.getText().toString();
                String contrasena_str = contrasena_txt.getText().toString();

                if(correo_str.equals("prueba") && contrasena_str.equals("prueba") ) {
                    Intent i = new Intent(Ingreso.this, ActivityPrincipal.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Ingreso.this, "Datos incorrectos, vuelva a intentarlo", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Ingreso.this, Registro.class);
                startActivity(i);
            }
        });

    }
    private void check_Permission() {

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(Ingreso.this,"Permiso Concedido",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent i = new Intent();
                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",getPackageName(),"");
                i.setData(uri);
                startActivity(i);

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();

            }
        }).check();
    }
    
}
