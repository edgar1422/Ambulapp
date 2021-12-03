package com.example.prueba_repo.Fragments_Usu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prueba_repo.Adaptadores.Adapter_tiposAccidentes;
import com.example.prueba_repo.Controlador.DatosReporte;
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
    DatabaseReference myRef2 = database.getReference("Reportes");

    private Spinner sp_tiposAccidentes;
    private tiposAccidentes objAccidentes;
    private Adapter_tiposAccidentes adapterAccidentes;
    private ArrayList<tiposAccidentes> AccidentesList;
    private EditText adress,et_fhone, observaciones;
    private FusedLocationProviderClient fusedLocationClient;
    private String fhone,tipo,comentario, buscar, direccion;
    private double latitud, longitud;
    private FirebaseAuth mAuth;
    private Button reporte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reporte);




        sp_tiposAccidentes = (Spinner) findViewById(R.id.sp_item);
        adress = (EditText) findViewById(R.id.direccion_acc);
        et_fhone = (EditText) findViewById(R.id.editTextPhone);
        observaciones = (EditText) findViewById(R.id.et_observaciones);
        reporte = (Button) findViewById(R.id.btn_reporte);
        mAuth = FirebaseAuth.getInstance();
        buscar = mAuth.getUid();


        String [] opciones = {"Accidente de tránsito con herido o lesionado","Persona herida en vía pública",
                "Dolor en el tórax","Intoxicación","Caída desde altura","Persona inconsciente","Persona que no respira o que tiene dificultad para respirar",
                "Persona con alteraciones en su comportamiento mental","Herido por arma blanca","Madre gestante en alto riesgo",
                "Herido por arma de fuego","Embarazos con trabajo de parto en curso"};

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opciones);
        sp_tiposAccidentes.setAdapter(adapter);

        //cargarLista();
        //adapterAccidentes = new Adapter_tiposAccidentes(this,AccidentesList);
        //sp_tiposAccidentes.setAdapter(adapterAccidentes);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fhone = snapshot.child("Usuario").child(buscar)
                        .child("fhone").getValue().toString();
                et_fhone.setText(fhone);


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
                        direccion = addresses.get(0).getAddressLine(0);
                        adress.setText(direccion);
                        latitud = addresses.get(0).getLatitude();
                        longitud = addresses.get(0).getLongitude();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        reporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo = sp_tiposAccidentes.getSelectedItem().toString();
                String comentario = observaciones.getText().toString();

                if (!fhone.isEmpty() && !tipo.isEmpty() && !direccion.isEmpty() && !comentario.isEmpty()){
                    DatosReporte datosReporte = new DatosReporte(fhone, latitud, longitud, comentario);
                    myRef2.child(tipo).setValue(datosReporte);
                    Toast.makeText(Activity_crearReporte.this,"Reporte realizado con exito",
                            Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Activity_crearReporte.this, Activity_Usuario.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(Activity_crearReporte.this, "Ningun campo debe estar vacio", Toast.LENGTH_SHORT).show();
                    }
            }
        });



    }

    public void cargarLista() {


        AccidentesList = new ArrayList<>();
        AccidentesList.add(new tiposAccidentes("Accidente de tránsito con herido o lesionado"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Persona herida en vía pública"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Dolor en el tórax"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Intoxicación"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Caída desde altura"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Persona inconsciente"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Persona que no respira o que tiene dificultad para respirar"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Persona con alteraciones en su comportamiento mental"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Herido por arma blanca"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Madre gestante en alto riesgo"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Herido por arma de fuego"/*, R.drawable.distancia*/));
        AccidentesList.add(new tiposAccidentes("Embarazos con trabajo de parto en curso"/*, R.drawable.distancia*/));


    }







}