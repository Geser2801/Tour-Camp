package com.rodr.tourcamp.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.rodr.tourcamp.Activities.CompraBoleto;
import com.rodr.tourcamp.Activities.Galerias_Calakmul;
import com.rodr.tourcamp.Activities.Juegos_Disponibles;
import com.rodr.tourcamp.Activities.Metodo_Pago;
import com.rodr.tourcamp.Activities.Restaurantes_Calakmul;
import com.rodr.tourcamp.Activities.Sitio_Calakmul;
import com.rodr.tourcamp.Contexto.AppFragments;
import com.rodr.tourcamp.R;

public class HomeFragment extends Fragment {

    GridLayout grdContenedorPrincipal;
    CardView cardOptionsHome;
    Intent intent;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        initGui();
        clickEventGridLayout();
        return view;
    }

    private void initGui(){
        grdContenedorPrincipal = (GridLayout) view.findViewById(R.id.grd_Contenedor_Home);
    }

    private void clickEventGridLayout(){
        for (int i = 0; i < grdContenedorPrincipal.getChildCount(); i++)
        {
            cardOptionsHome = (CardView) grdContenedorPrincipal.getChildAt(i);
            int iterator = i;
            cardOptionsHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(AppFragments.context, "Click " + iterator, Toast.LENGTH_SHORT).show();
                    switch (iterator)
                    {
                        case 0:
                            intent = new Intent(AppFragments.context, Sitio_Calakmul.class);
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(AppFragments.context, Metodo_Pago.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(AppFragments.context, Juegos_Disponibles.class);
                            startActivity(intent);
                            break;
                        case 3:
                            //intent = new Intent(AppFragments.context, Galerias_Calakmul.class);
                            //startActivity(intent);
                            break;
                    }
                }
            });
        }
    }
}
