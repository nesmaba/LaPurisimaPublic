package org.nestordevelopments.lapurisima;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Base2f extends AppCompatActivity {

    static CursosAsistenciaFragment fragmentCursosAsistencias;
    static AlumnosAsistenciaFragment fragmentAlumnosAsistencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base2f);

        Intent intent = this.getIntent();

        if(intent != null){
            String boton= intent.getStringExtra("BotonPulsado");
            if(boton.equals("Asistencias")) {
                fragmentCursosAsistencias = new CursosAsistenciaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutBase1, fragmentCursosAsistencias).commit();
                fragmentAlumnosAsistencias = new AlumnosAsistenciaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutBase2, fragmentAlumnosAsistencias).commit();
            }
        }
    }
}
