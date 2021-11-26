package com.example.prueba_repo.Controlador;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba_repo.Fragments_Amb.Activity_Ambulancia;
import com.example.prueba_repo.Fragments_Usu.Activity_Usuario;
import com.example.prueba_repo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Ambulancia extends Fragment {


    EditText email_amb, password_amb,f_name_amb,l_name_amb,city_amb,placas_amb,dir_amb,fhone_amb;  //firebase
    Button register_amb;

    private static final String TAG = "tester";
    private FirebaseAuth mAuth;  //firebase


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Usuarios");

    public Ambulancia() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ambulancia, container, false);


        // Iniciando firebase
        mAuth = FirebaseAuth.getInstance();
        email_amb = (EditText) root.findViewById(R.id.email_amb);
        password_amb = (EditText) root.findViewById(R.id.password_amb);
        register_amb = (Button) root.findViewById(R.id.btguardar_amb);
        f_name_amb = (EditText) root.findViewById(R.id.nombre_amb);
        l_name_amb = (EditText) root.findViewById(R.id.apellidos_amb);
        city_amb = (EditText) root.findViewById(R.id.ciudad_amb);
        placas_amb = (EditText) root.findViewById(R.id.placas_amb);
        dir_amb = (EditText) root.findViewById(R.id.direccion_amb);
        fhone_amb = (EditText) root.findViewById(R.id.telefono_amb);




        register_amb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_amb.getText().toString();
                String password = password_amb.getText().toString();
                String name = f_name_amb.getText().toString();
                String last_name = l_name_amb.getText().toString();
                String city = city_amb.getText().toString();
                String placas = placas_amb.getText().toString();
                String dirreccion = dir_amb.getText().toString();
                String fhone = fhone_amb.getText().toString();
                String eps= "********";
                String roll = "ambulancia";
                String born="********";

                try{


                    if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !last_name.isEmpty()
                            && !city.isEmpty() && !placas.isEmpty() && !dirreccion.isEmpty() && !fhone.isEmpty()){
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information

                                            FirebaseUser user = mAuth.getCurrentUser();
                                            String id = user.getUid();

                                            DatosUsuario ambulancia = new DatosUsuario(name,last_name,email,
                                                    password,city,dirreccion,fhone,placas);


                                            myRef.child("Ambulancia").child(id).setValue(ambulancia);;
                                            //Log.d(TAG, ambulancia.toString());

                                            Log.d(TAG, "createUserWithEmail:success");


                                            updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(getActivity(),"Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            updateUI(null);
                                        }
                                    }
                                });
                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(),"Ningun campo debe estar vacio",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //if(currentUser != null){
        //reload();
    }

    private void updateUI(FirebaseUser user) {                                // se usa para el login
        if (user != null){
            Log.d("tester","test");
            Toast.makeText(getActivity(), "Usuario creado con exito ", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), Activity_Ambulancia.class);
            startActivity(i);
        }else{
            Toast.makeText(getActivity(), "No se pudo crear el usario ", Toast.LENGTH_SHORT).show();

        }
    }
}
