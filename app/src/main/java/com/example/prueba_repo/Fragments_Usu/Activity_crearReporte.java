package com.example.prueba_repo.Fragments_Usu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.prueba_repo.Adaptadores.Adapter_tiposAccidentes;
import com.example.prueba_repo.Entidades.tiposAccidentes;
import com.example.prueba_repo.R;

import java.util.ArrayList;


public class Activity_crearReporte extends AppCompatActivity {

    private Spinner sp_tiposAccidentes;
    private tiposAccidentes objAccidentes;
    private Adapter_tiposAccidentes adapterAccidentes;
    private ArrayList<tiposAccidentes> AccidentesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reporte);


        sp_tiposAccidentes = (Spinner) findViewById(R.id.sp_item);

        cargarLista();
        adapterAccidentes = new Adapter_tiposAccidentes(this,AccidentesList);
        sp_tiposAccidentes.setAdapter(adapterAccidentes);
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