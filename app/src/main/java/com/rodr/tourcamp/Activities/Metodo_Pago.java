package com.rodr.tourcamp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.rodr.tourcamp.DialogFragment.AgregarMetodoPagoDialogFragment;
import com.rodr.tourcamp.R;

public class Metodo_Pago extends AppCompatActivity {

    MaterialButton btnAddMetodoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago);
        initGui();
        irDialogoMetodoPagoFragment();
    }

    private void initGui(){
        btnAddMetodoPago = (MaterialButton) findViewById(R.id.btn_Add_Metodo_Pago);
    }

    private void irDialogoMetodoPagoFragment(){
        btnAddMetodoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarMetodoPagoDialogFragment addMetodoPago = new AgregarMetodoPagoDialogFragment();
                addMetodoPago.show(getSupportFragmentManager(), "Ir a dialogo");
            }
        });
    }
}