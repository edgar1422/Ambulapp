package com.example.prueba_repo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba_repo.Controlador.PagerController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2;
    EditText email_amb,password_amb;  //firebase
    Button register_amb;
    private static final String TAG = "tester";                                // se usa para el login


    PagerController pagerAdapter;

    private FirebaseAuth mAuth;  //firebase
// ...



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        tab1 = findViewById(R.id.tabusuario);
        tab2 = findViewById(R.id.tabambulancia);

        // Iniciando firebase
        mAuth = FirebaseAuth.getInstance();
        email_amb = (EditText) findViewById(R.id.email_amb);
        password_amb = (EditText)findViewById(R.id.password_amb);
        register_amb = (Button) findViewById(R.id.btguardar_amb);

        pagerAdapter = new PagerController(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    Toast.makeText(Registro.this, "Registro como usuario", Toast.LENGTH_SHORT).show();
                    pagerAdapter.notifyDataSetChanged();

                }
                if(tab.getPosition()==1) {
                    Toast.makeText(Registro.this, "Registro como conductor de ambulancia", Toast.LENGTH_SHORT).show();
                    pagerAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void register_amb (View view){

        String email = email_amb.getText().toString();
        String password = password_amb.getText().toString();



        try{
            if (email != null && password != null){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Registro.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
            }

        } catch (Exception e) {
            Toast.makeText(this,"Los campos 'correo' y 'contraseña' estan vacios",
                    Toast.LENGTH_SHORT).show();
        }

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
            Toast.makeText(this, "Usuario creado con exito ", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ActivityPrincipal.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "No se pudo crear el susario ", Toast.LENGTH_SHORT).show();

        }
    }

}