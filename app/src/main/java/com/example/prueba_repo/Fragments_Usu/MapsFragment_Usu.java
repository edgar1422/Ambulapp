package com.example.prueba_repo.Fragments_Usu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapsFragment_Usu extends Fragment {

    Button crear_Reportes;
    FusedLocationProviderClient fusedLocationClient;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps_usu, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);


        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;}
                googleMap.setMyLocationEnabled(true);


                fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();

                        if (location != null){
                            try {
                                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(
                                        location.getLatitude(),location.getLongitude(),1
                                );
                                LatLng miUbicacion = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                                googleMap.addMarker(new MarkerOptions().position(miUbicacion).title(addresses.get(0).getAddressLine(0)));
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion,17));
                                googleMap.getUiSettings().setZoomControlsEnabled(true);



                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });



                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {

                        LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(miUbicacion).title("Mi ubicación"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion,14));
                        googleMap.getUiSettings().setZoomControlsEnabled(true);

                    }
                };

            }

        });

        crear_Reportes = (Button) view.findViewById(R.id.btn_crearReportes);
        crear_Reportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Todos debemos hacer uso responsable" +
                        " de los servicios de ambulancias para salvar vidas ", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), Activity_crearReporte.class);

                startActivity(i);

            }

        });

        return view;
    }

}

