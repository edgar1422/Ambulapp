package com.example.prueba_repo.Fragments_Amb;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prueba_repo.Ayuda_Fragment;
import com.example.prueba_repo.Configuraciones_Fragment;
import com.example.prueba_repo.Ingreso;
import com.example.prueba_repo.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Ambulancia extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        DrawerLayout mDrawerLayout;
        NavigationView navigationView;
        Toolbar toolbar_amb;
        private FirebaseAuth mAuth;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ambulancia);

            mDrawerLayout = findViewById(R.id.drawerLayout);
            navigationView = findViewById(R.id.nav_view);
            toolbar_amb = findViewById(R.id.toolbar_amb);

            // ...
        // Initialize Firebase Auth
            mAuth = FirebaseAuth.getInstance();


            getSupportFragmentManager().beginTransaction().add(R.id.content, new MapsFragment_Amb()).commit();
            setTitle("Inicio");
            // setup toolbar
            setSupportActionBar(toolbar_amb);
            navigationView.setNavigationItemSelectedListener(this);


            final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

            findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }

            });

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setItemIconTintList(null);


        }


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectItemNav(item);
            return true;
        }

        private void selectItemNav(MenuItem item) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            switch (item.getItemId()) {

                case R.id.nav_Inicio:
                    ft.replace(R.id.content, new MapsFragment_Amb()).commit();
                    break;
                case R.id.nav_profile:
                    ft.replace(R.id.content, new Profile_Fragment_Amb()).commit();
                    break;
                case R.id.nav_notification:
                    ft.replace(R.id.content, new Notificaciones_Fragment_Amb()).commit();
                    break;
                case R.id.nav_history:
                    ft.replace(R.id.content, new Historial_Fragment_Amb()).commit();
                    break;
                case R.id.nav_configuration:
                    ft.replace(R.id.content, new Configuraciones_Fragment()).commit();
                    break;
                case R.id.nav_help:
                    ft.replace(R.id.content, new Ayuda_Fragment()).commit();
                    break;
                case R.id.nav_sign_out:
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(this, Ingreso.class);
                    startActivity(i);
                    finish();
                    break;

            }
            setTitle(item.getTitle());
            mDrawerLayout.closeDrawers();
        }

    }