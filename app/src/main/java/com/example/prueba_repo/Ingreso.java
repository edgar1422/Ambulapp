package com.example.prueba_repo;

import android.Manifest;
import android.content.Intent;
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

import com.example.prueba_repo.Fragments_Amb.Activity_Ambulancia;
import com.example.prueba_repo.Fragments_Usu.Activity_Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class Ingreso extends AppCompatActivity {

    private EditText correo_txt, contrasena_txt;
    private Button btn_login, btn_register;
    private FirebaseAuth mAuth;                                                // se usa para el login
    private static final String TAG = "tester";                                // se usa para el login
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Usuarios");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        correo_txt = (EditText) findViewById(R.id.et_correo);
        contrasena_txt = (EditText) findViewById(R.id.et_contrasena);
        btn_login = (Button) findViewById(R.id.button2);
        btn_register = (Button) findViewById(R.id.button3);
        check_Permission();
        mAuth = FirebaseAuth.getInstance();                                         // se usa para el login

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Ingreso al boton");

                String correo_str = correo_txt.getText().toString();                // se usa para el login
                String contrasena_str = contrasena_txt.getText().toString();        // se usa para el login

                signIn(correo_str,contrasena_str);                                   // se usa para el login

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
    public void onStart() {                                                       // se usa para el login
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void signIn(String email, String password) {                          // se usa para el login
        try{
            if (email != null && password != null){
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
                                    Toast.makeText(Ingreso.this, "Datos incorrectos, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
                // [END sign_in_with_email]
            }

        } catch (Exception e) {
            Toast.makeText(this,"Ningun campo debe estar vacio",Toast.LENGTH_LONG).show();

        }
    }

    private void updateUI(FirebaseUser user) {                                // se usa para el login
        if (user != null){
            String userId = user.getUid();


            Log.d("Test",userId);
            myRef.child("Usuario").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("firebase2", String.valueOf(task.getResult().getValue()));
                        String user = String.valueOf(task.getResult().getValue());
                        if (!user.equals("null")){
                            // Log.d("tester","test");
                            Toast.makeText(Ingreso.this, "Ingreso exitoso", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Ingreso.this, Activity_Usuario.class);
                            startActivity(i);
                        }myRef.child("Ambulancia").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                Log.d("firebase2", String.valueOf(task.getResult().getValue()));
                                String user = String.valueOf(task.getResult().getValue());
                                if(!user.equals("null")){
                                    Toast.makeText(Ingreso.this, "Ingreso exitoso", Toast.LENGTH_SHORT).show();
                                    Intent a = new Intent(Ingreso.this, Activity_Ambulancia.class);
                                    startActivity(a);
                                }
                            }
                        });
                    }
                    else {
                        Log.d("firebase2", "Error getting data", task.getException());
                    }
                }
            });


        }else{
            Toast.makeText(Ingreso.this, "El usuario no existe", Toast.LENGTH_SHORT).show();

        }





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
