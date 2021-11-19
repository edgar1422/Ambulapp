package com.example.prueba_repo.Controlador;

import static android.content.ContentValues.TAG;

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

import com.example.prueba_repo.ActivityPrincipal;
import com.example.prueba_repo.DatosAmbulancia;
import com.example.prueba_repo.R;
import com.example.prueba_repo.Registro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ambulancia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ambulancia extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText email_amb, password_amb;  //firebase
    Button register_amb;
    private static final String TAG = "tester";
    private FirebaseAuth mAuth;  //firebase
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Usuarios");

    public Ambulancia() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ambulancia.
     */
    // TODO: Rename and change types and number of parameters
    public static Ambulancia newInstance(String param1, String param2) {
        Ambulancia fragment = new Ambulancia();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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


        register_amb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_amb.getText().toString();
                String password = password_amb.getText().toString();


                try{
                    if (email != null && password != null){
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information

                                            DatosAmbulancia ambulancia = new DatosAmbulancia(email,password);
                                            myRef.push().setValue(ambulancia);
                                            Log.d(TAG, ambulancia.toString());

                                            Log.d(TAG, "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();

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
                    Toast.makeText(getActivity(),"Los campos 'correo' y 'contraseña' estan vacios",
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
            Intent i = new Intent(getActivity(), ActivityPrincipal.class);
            startActivity(i);
        }else{
            Toast.makeText(getActivity(), "No se pudo crear el susario ", Toast.LENGTH_SHORT).show();

        }
    }
}
