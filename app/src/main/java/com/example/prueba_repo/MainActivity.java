package com.example.prueba_repo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nota1_txt;
    private EditText nota2_txt;
    private EditText nota3_txt;
    private EditText nota4_txt;
    private TextView resultado_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        nota1_txt = (EditText) findViewById(R.id.nota1);
        nota2_txt = (EditText) findViewById(R.id.nota2);
        nota3_txt = (EditText) findViewById(R.id.nota3);
        nota4_txt = (EditText) findViewById(R.id.nota4);
        resultado_txt = (TextView) findViewById(R.id.resultado);
    }
    public void calcular(View view) {

        String nota1_str = nota1_txt.getText().toString();
        String nota2_str = nota2_txt.getText().toString();
        String nota3_str = nota3_txt.getText().toString();
        String nota4_str = nota4_txt.getText().toString();

        float nota1_float = Float.parseFloat(nota1_str);
        float nota2_float = Float.parseFloat(nota2_str);
        float nota3_float = Float.parseFloat(nota3_str);
        float nota4_float = Float.parseFloat(nota4_str);

        float promedio = (nota1_float + nota2_float + nota3_float + nota4_float)/4;

        if (promedio >= 3) {
            resultado_txt.setText("Aprobó " + promedio);
        } else {
            resultado_txt.setText("Reprobó " + promedio);
        }
    }
}



