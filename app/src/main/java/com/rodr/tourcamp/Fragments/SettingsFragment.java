package com.rodr.tourcamp.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.rodr.tourcamp.R;
import com.rodr.tourcamp.Activities.Perfil_User;
import com.rodr.tourcamp.Contexto.AppFragments;

public class SettingsFragment extends Fragment {
    View view;
    Button btnPerfilUser;
    GridLayout grdAjustesPerfil;
    CardView cardAjustesPerfil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_fragment, container, false);
        initGui();
        irPerfilUser();
        return view;
    }

    private void initGui() {
        //btnPerfilUser = (Button) view.findViewById(R.id.btn_Perfil_User);
        grdAjustesPerfil = (GridLayout) view.findViewById(R.id.grd_Ajustes_Perfil);
        cardAjustesPerfil = (CardView) view.findViewById(R.id.card_Ajustes_Perfil);
    }

    // BOTON DE IR AL PERFIL DE USUARIO...
    private void irPerfilUser(){
        /*btnPerfilUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppFragments.context, Perfil_User.class);
                startActivity(intent);
            }
        }); */

        grdAjustesPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppFragments.context, Perfil_User.class);
                startActivity(intent);
            }
        });
    }

}
