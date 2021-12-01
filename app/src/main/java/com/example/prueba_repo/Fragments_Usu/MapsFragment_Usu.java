package com.example.prueba_repo.Fragments_Usu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.prueba_repo.Ayuda_Fragment;
import com.example.prueba_repo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsFragment_Usu extends Fragment {

    Button crear_Reportes;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps_usu, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);


        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {


                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    LocationListener locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(miUbicacion).title("Mi ubicación"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(miUbicacion)
                                    .zoom(14)
                                    .bearing(90)
                                    .tilt(45)
                                    .build();
                            //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 14));
                        }
                    };
                    return;
                }
                googleMap.setMyLocationEnabled(true);
            }

        });

        crear_Reportes = (Button) view.findViewById(R.id.btn_crearReportes);
        crear_Reportes.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Todos debemos hacer uso responsable de los servicios de ambulancias para salvar vidas ", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), Activity_crearReporte.class);
                startActivity(i);

            }

        });

        return view;
    }

}

