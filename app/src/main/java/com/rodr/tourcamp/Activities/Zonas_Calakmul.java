package com.rodr.tourcamp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rodr.tourcamp.R;

public class Zonas_Calakmul extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_calakmul);

        CardView card_Zona_Xpuhil;
        CardView card_Zona_Becan;
        card_Zona_Becan = (CardView) findViewById(R.id.card_Zona_Becan);
        card_Zona_Xpuhil = (CardView) findViewById(R.id.card_Zona_Xpuhil);

        card_Zona_Xpuhil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Informatio_Xpujil.class);
                startActivity(i);
            }
        });

        Button btnIrPa;
        btnIrPa = (Button) findViewById(R.id.btnIrPa);

        btnIrPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CompraBoleto.class);
                startActivity(i);
            }
        });

        card_Zona_Becan = (CardView) findViewById(R.id.card_Zona_Becan);

        card_Zona_Becan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Becan.class);
                startActivity(i);
            }
        });

    }
}