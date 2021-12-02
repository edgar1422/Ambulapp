package com.example.prueba_repo.Fragments_Usu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.prueba_repo.Adaptadores.Adapter_tiposAccidentes;
import com.example.prueba_repo.Entidades.tiposAccidentes;
import com.example.prueba_repo.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Activity_crearReporte extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Usuarios");

    private Spinner sp_tiposAccidentes;
    private tiposAccidentes objAccidentes;
    private Adapter_tiposAccidentes adapterAccidentes;
    private ArrayList<tiposAccidentes> AccidentesList;
    private EditText adress,fhone;
    private FusedLocationProviderClient fusedLocationClient;
    private String direcccion, buscar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reporte);




        sp_tiposAccidentes = (Spinner) findViewById(R.id.sp_item);
        adress = (EditText) findViewById(R.id.direccion_acc);
        fhone = (EditText) findViewById(R.id.editTextPhone);
        mAuth = FirebaseAuth.getInstance();
        buscar = mAuth.getUid();


        cargarLista();
        adapterAccidentes = new Adapter_tiposAccidentes(this,AccidentesList);
        sp_tiposAccidentes.setAdapter(adapterAccidentes);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.child("Usuario").child(buscar)
                        .child("fhone").getValue().toString();
                fhone.setText(value);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;}
        fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(Activity_crearReporte.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        direcccion = addresses.get(0).getAddressLine(0);
                        adress.setText(direcccion);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }

    public void cargarLista() {
        AccidentesList = new ArrayList<>();
        AccidentesList.add(new tiposAccidentes("Accidente de tránsito con herido o lesionado", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Persona herida en vía pública", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Dolor en el tórax", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Intoxicación", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Caída desde altura", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Persona inconsciente", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Persona que no respira o que tiene dificultad para respirar", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Persona con alteraciones en su comportamiento mental", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Herido por arma blanca", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Madre gestante en alto riesgo", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Herido por arma de fuego", R.drawable.distancia));
        AccidentesList.add(new tiposAccidentes("Embarazos con trabajo de parto en curso", R.drawable.distancia));

    }
}