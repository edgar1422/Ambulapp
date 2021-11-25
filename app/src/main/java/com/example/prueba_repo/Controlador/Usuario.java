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


public class Usuario extends Fragment {

    EditText name, l_name, email, password, fhone, eps, born_date, city, direccion;
    Button register_usu;

    private static final String TAG = "tester";
    private FirebaseAuth mAuth;  //firebase

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Usuario");

    public Usuario() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_usuario, container, false);


        mAuth = FirebaseAuth.getInstance();
        name = (EditText) root.findViewById(R.id.nombre_usu);
        l_name = (EditText) root.findViewById(R.id.apellidos_usu);
        email = (EditText) root.findViewById(R.id.email_usu);
        password = (EditText) root.findViewById(R.id.password_usu);
        eps = (EditText) root.findViewById(R.id.eps_usu);
        born_date = (EditText) root.findViewById(R.id.fecha_nacimiento_usu);
        city = (EditText) root.findViewById(R.id.ciudad_usu);
        direccion = (EditText) root.findViewById(R.id.direccion_usu);
        fhone = (EditText) root.findViewById(R.id.telefono_usu);
        register_usu = (Button) root.findViewById(R.id.btguardar_usu);

        register_usu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_name = name.getText().toString();
                String s_l_name = l_name.getText().toString();
                String s_email = email.getText().toString();
                String s_password = password.getText().toString();
                String s_eps = eps.getText().toString();
                String s_born_date = born_date.getText().toString();
                String s_city = city.getText().toString();
                String s_direccion = direccion.getText().toString();
                String s_fhone = fhone.getText().toString();
                String s_placas= "******";
                String s_roll= "usuario";

                try{


                    if (!s_email.isEmpty() && !s_password.isEmpty() && !s_name.isEmpty() && !s_l_name.isEmpty()
                            && !s_city.isEmpty() && !s_eps.isEmpty() && !s_direccion.isEmpty() && !s_born_date.isEmpty() && !s_fhone.isEmpty()){
                        mAuth.createUserWithEmailAndPassword(s_email, s_password)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information

                                            FirebaseUser user = mAuth.getCurrentUser();
                                            String id = user.getUid();

                                            DatosUsuario usuario = new DatosUsuario(id, s_name,s_l_name,s_email,s_password,s_eps,
                                                    s_born_date,s_city,s_direccion,s_fhone,s_placas,s_roll);
                                            myRef.push().setValue(usuario);
                                            Log.d(TAG, usuario.toString());

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



        return  root;
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
            Intent i = new Intent(getActivity(), Activity_Usuario.class);
            startActivity(i);
        }else{
            Toast.makeText(getActivity(), "No se pudo crear el usario ", Toast.LENGTH_SHORT).show();

        }
    }
}