package com.rodr.tourcamp.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rodr.tourcamp.Activities.MapsActivity;
import com.rodr.tourcamp.Adapter_Maps.Maps;
import com.rodr.tourcamp.Adapter_Maps.ReciclerAdapterMaps;
import com.rodr.tourcamp.R;
import com.rodr.tourcamp.Contexto.AppFragments;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

    View view;
    private RecyclerView recyclerMaps; // OBJETO DE TIPO DE RECYCLERVIEW...
    private RecyclerView.Adapter adapterMaps; // ADAPTOR RECYCLER....
    private RecyclerView.LayoutManager lManager; // LAYOUT...

    ArrayList<Maps> arrayMaps = new ArrayList<>();

    // ARREGLO DONDE SE VISUALIZA EL TITULO EN EL RECYCLER VIEW...
    String [] arrayLugaresTuristicos = {
            "Calakmul",
            "Zona Arqueológica de Becán",
            "Zona Arqueológica Xpuhil",
            "Zona Arqueológica de Balamkú",
            "Zona Arqueológica de Hormiguero"
    };

    // INICIALIZAR MAPS
    List itemsMaps = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maps_fragment, container, false);
        initGui();
        inicializarRecicler();
        cargarDatosRecicler();
        return view;
    }

    private void initGui(){
        // OBTENER EL RECYCLER....
        recyclerMaps = (RecyclerView) view.findViewById(R.id.recicler_Maps);
    }

    // OBTENER EL TAMAÑO DEL RECYCLER...
    private void inicializarRecicler(){
        recyclerMaps.setHasFixedSize(true);
    }

    // LLENAR EL RECYCLER (ARREGLO)....
    private void cargarDatosRecicler(){

        // LLENADO MANUALMENTE, PASANDOLE CADA ARGUMENTO.....
        /*itemsMaps.add(new Maps(R.drawable.ic_pos_sities_maps, "Calakmul", "Reserva de la biofesra de calakmul"));
        itemsMaps.add(new Maps(R.drawable.ic_pos_sities_maps, "Calakmul", "Zona Arqueologica de Balamkù"));
        itemsMaps.add(new Maps(R.drawable.ic_pos_sities_maps, "Calakmul", "Zona Arqueologica Rio Bec"));
        itemsMaps.add(new Maps(R.drawable.ic_pos_sities_maps, "Calakmul", "Ka´an Expeditions - Day Tours"));*/

        // LLENARLO MEEDIANTE UN CICLO....
        for (int i = 0; i < arrayLugaresTuristicos.length; i++){
            itemsMaps.add(new Maps(R.drawable.ic_pos_sities_maps, "Calakmul", arrayLugaresTuristicos[i]));
        }

        // USAR UN ADMINISTRADOR PARA LinearLayout.....
        lManager = new LinearLayoutManager(AppFragments.context, LinearLayoutManager.HORIZONTAL, false);
        recyclerMaps.setLayoutManager(lManager);

        // CREAR UN NUEVO ADAPTADOR.....
        adapterMaps = new ReciclerAdapterMaps(itemsMaps); // MANDAMOS TODOS LOS DATOS AL ADAPTADOR
        recyclerMaps.setAdapter(adapterMaps); // VISUALIZAMOS LOS DATOS....
    }
}
