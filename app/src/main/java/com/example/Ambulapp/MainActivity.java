package com.example.Ambulapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenida);

        timer = new Timer ();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent (MainActivity.this, Ingreso.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}

