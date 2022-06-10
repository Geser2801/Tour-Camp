package com.rodr.tourcamp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rodr.tourcamp.Contexto.AppFragments;
import com.rodr.tourcamp.R;

public class Sitio_Calakmul extends AppCompatActivity {

    //intent = new Intent(AppFragments.context, Galerias_Calakmul.class);
    //startActivity(intent);
    Intent intent;
    CardView cardZonasArquelogicas, cardRestaurantes, cardGalerias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitio_calakmul);
        initGui();
        irGaleriaCalakmul();
        irRestaurantes();
        irZonasCalakmul();
    }

    private void initGui(){
        cardZonasArquelogicas = (CardView) findViewById(R.id.card_Zonas_Arquelogicas);
        cardRestaurantes = (CardView) findViewById(R.id.card_Restaurantes);
        cardGalerias = (CardView) findViewById(R.id.card_Galerias);
    }

    private void irGaleriaCalakmul(){
        cardGalerias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Galerias_Calakmul.class);
                startActivity(intent);
            }
        });
    }

    private void irRestaurantes(){
        cardRestaurantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AppFragments.context, Restaurantes_Calakmul.class);
                startActivity(intent);
            }
        });
    }

    private void irZonasCalakmul(){
        cardZonasArquelogicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AppFragments.context, Zonas_Calakmul.class);
                startActivity(intent);
            }
        });
    }
}