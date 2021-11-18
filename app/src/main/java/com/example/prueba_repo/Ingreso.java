package com.example.prueba_repo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class Ingreso extends AppCompatActivity {

    private EditText correo_txt, contrasena_txt;
    private Button btn_login, btn_register;
    private FirebaseAuth mAuth;
    private static final String TAG = "tester";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        correo_txt = (EditText) findViewById(R.id.et_correo);
        contrasena_txt = (EditText) findViewById(R.id.et_contrasena);
        btn_login = (Button) findViewById(R.id.button2);
        btn_register = (Button) findViewById(R.id.button3);
        check_Permission();
        mAuth = FirebaseAuth.getInstance();




        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Ingreso al boton");

                String correo_str = correo_txt.getText().toString();
                String contrasena_str = contrasena_txt.getText().toString();

                signIn(correo_str,contrasena_str);
               /* if(correo_str.equals("prueba") && contrasena_str.equals("prueba") ) {


                    Intent i = new Intent(Ingreso.this, ActivityPrincipal.class);
                   startActivity(i);
                } else {
                    //Toast.makeText(Ingreso.this, "Datos incorrectos, vuelva a intentarlo", Toast.LENGTH_LONG).show();
                }*/

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
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        Log.d(TAG, "Ingreso al login");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {

// If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithEmail:success");
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Ingreso.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void updateUI(FirebaseUser user) {
        Log.d("tester","test");
        Toast.makeText(Ingreso.this, "Actualizado ",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Ingreso.this, ActivityPrincipal.class);
        startActivity(i);
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
