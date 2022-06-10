package com.rodr.tourcamp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rodr.tourcamp.R;

public class Juegos_Disponibles extends AppCompatActivity {

    Button btn_Gato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_disponibles);
        initGui();
        irGato();
    }

    private void initGui(){
        btn_Gato = (Button) findViewById(R.id.btn_Gato);
    }

    private void irGato(){
        btn_Gato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
btn_Gato.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), TicTacToe.class);
        startActivity(intent);
    }
});
            }
        });
    }
}