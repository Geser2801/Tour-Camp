package com.rodr.tourcamp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rodr.tourcamp.R;
import com.rodr.tourcamp.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // OBJETO DE GOOGLE MAPS....
    private ActivityMapsBinding binding; // OBJETO DE VISTA DE FRAGMENTO....
    String valorIntent; // VARIABLE LA CUAL ME PERMITE SABER QUE VALOR ME TRAE LA ACTIVITY (INTENT)....

    /*
        ARREGLO DE UBICACIONES....
        1ER ARREGLO TITULOS DE LOS LUGARES A VISUALIZAR...
        2DO COORDENADAS DE LOS LUGARES OBTENIDOS DESDE EL GOOGLE MAPS...
    */
    String [] lugar1 = {
            "RehabilitARTE ", "CENTIR CAMPECHE", "Centro de Fisioterapia y Rehabilitación CEFIR","CLINICA DE FISIOTERAPIA KINEOGENESIS",
            "CITEF", "FISIOKENESIS Fisioterapia & Osteopatia", "Clínica de Fisioterapia UAC",
            "Facultad De Fisioterapia-UAC", "Ceracom Campeche", "DIVACAM Distribuidora de Vacunas de Campeche"
    };
    double [] coorLugar1 = {
            19.8533004,-90.5315972, 19.812288,-90.5351728, 19.834247,-90.5476867, 19.8342916,-90.5630077, 19.839727969052024,-90.50824282368166, 19.8394357,-90.5454922,
            19.8296935,-90.5586434, 19.8264056,-90.5587163, 19.8239894,-90.5291708, 19.8327156,-90.5548426
    };

    String [] lugar2 = {
            "FISIOPRAXIA Rehabilitación y Fisioterapia sur Mérida Dolores Otero", "FISIO CARE", "Clínica de Mérida - Fisioterapia, Rehabilitación y Deporte","Clinica de Fisioterapia FISIOPLUS",
            "Fisiomer City / Recovery Center", "Physios Fisioterapia y Rehabilitación",
            "Fisiomer", "RehabilitaZone", "Fisio Mayab", "FisioCare Star Médica"
    };
    double [] coorLugar2 = {
            20.9401344,-89.6250747, 21.0042177,-89.617988, 20.9859559,-89.6405753, 21.007485,-89.6582189, 21.0297258,-89.6059356, 20.9936742,-89.6341299,
            20.9974671,-89.6304136, 21.0089522,-89.6357764, 20.9904408,-89.6070281, 21.0161957,-89.5871899
    };

    String [] lugar3 = {
            "Fisioterapia Especializada by Therapik", "Core Crystal Fisioterapia Cancun", "Terapia Fisica en Cancun","KKYNESIUM TERAPIA FISICA Y REHABILITACION",
            "Fisiohealth rehabilitacion integral", "Heilenmed", "Terapia Fisica Cancun (Teramel)", "Terapia Física Quirúrgica del Sur", "Terapia Física y Rehabilitación Cancún", "Rehavity Cancùn"
    };
    double [] coorLugar3 = {
            21.1377728,-86.8531382, 21.1659983,-86.8259361, 21.1737992,-86.8620382, 21.158249,-86.8300203, 21.1383238,-86.8735044, 21.1587241,-86.8319838,
            21.1591066,-86.8286971, 21.158695,-86.8594617, 21.1618327,-86.8370401, 21.1391122,-86.8541516
    };

    String [] sitioCalakmulNombres = {
            "Calakmul", "Zona Arqueológica de Becán", "Zona Arqueológica Xpuhil", "Zona Arqueológica de Balamkú", "Zona Arqueológica de Hormiguero"
    };
    double [] sitioCalakmulCoordenadas = {
            18.04026,-89.67338, 18.5197946806789, -89.46621790490097, 18.509296377535616, -89.40089518187253, 18.557737952060215, -89.94524764584915, 18.408780464613606, -89.49072563236066
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent(); // OBJETO DE TIPO INTENT....
        valorIntent = intent.getStringExtra("valor").toString(); // TRAE LA INFORMACION QUE CONTENGA EL INTENT EN LA CLASE RECICLERADAPATERMAPS...
        //Toast.makeText(getApplicationContext(), "Valor " + valorIntent, Toast.LENGTH_SHORT).show();

        // CREAMOS LA INSTANCIA DE LA CLASE DE VINVULACION PARA QUE USE EL FRAGMENTO....
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Toast.makeText(getApplicationContext(), "Valor " + valorIntent, Toast.LENGTH_SHORT).show();
        // SI EL VALOR OBTENIDO ES IGUAL A LA CLASE REYCLER (EVENTO ONCLICKLISTENER) ENTRA SEGUN SEA SU CASO....
        switch (valorIntent){
            case "0":
                ubicacionCalakmul(18.859033596034042, -89.51846202416495);
                break;
            case "1":
                ubicacionZonaBecán(18.51886210010484, -89.46587026039154);
                break;
            case "2":
                ubicacionZonaXpuhil(18.509246516989485, -89.40091991886794);
                break;
            case "3":
                ubicacionZonaBalamkú(18.557727781086825, -89.94524764584915);
                break;
            case "4":
                ubicacionZonaHormiguero(18.408892442551764, -89.49071490352432);
                break;
        }
        //marcadorMaps();

        /*LatLng sidney = new LatLng(-44, 151);
        //mMap.setMapStyle(GoogleMap.MAP_TYPE_HYBRID);
        mMap.addMarker(new MarkerOptions().position(sidney).title("Map"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sidney));*/
    }

    // OBTENER LAS UBICACIONES DE UNA CIUDAD...
    private void createMaker1() {
        marcadorUbicacionPrincipalMaps(19.8448109, -90.5230914);

        int position = 0; // VALOR DE INCREMENTO DE POSICION DE COORDENADAS....
        boolean valor = true; // BANDERA....

        for (String i: lugar1) { // RECORREMOS TODOS LOS LUGARES QUE ASIGNAMOS EN EL ARREGLO.....
            //while (valor) {
            LatLng coodenadas = new LatLng(coorLugar1[position], coorLugar1[position + 1]); // ASIGNAMOS SUS COORDENADAS...
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.pir2)).anchor(0.0f, 1.0f).position(coodenadas).title(i)); // MANDAMOS SUS COORDENADAS Y SUS TITULOS...
            position = position + 2; // AUMENTAMOS EL INCREMENTO DE POSICION DE COORDENADAS....
                //valor = false;
            //}
            //valor = true;
        }
        /*
            EJEMPLO DE RECORRIDO...

            1RA VUELTA...
            TITULO LUGAR ARRARY...
                - PARA LUGAR1 = RehabilitARTE;
            COORDENADAS DE ASIGNACION...
                - COORDENADAS = (0 = 19.8533004, 1 = 90.5315972)

            AUMENTAR 1 EN LUGAR Y 2 EN POSITION...

            2DA VUELTA...
            TITULO LUGAR ARRARY...
                - PARA LUGAR1 = CENTIR CAMPECHE;
            COORDENADAS DE ASIGNACION...
                - COORDENADAS = (2 = 19.812288, 3 = -90.5351728)

            HASTA N VUELTAS DEL ARREGLO....
        */

    }

    private void marcadorUbicacionPrincipalMaps(double latitude, double longitude) {

        // CREAMOS EL MARCADOR PRICIPAL O RUTA INICIAL DONDE SE VA A UBICAR....
        LatLng coordenadasPrincipal = new LatLng(latitude, longitude);

        // CREAMOS UN ICONO PERSONALIZADO PARA VISUALIZAR EN EL MAPS....
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.pir2)).anchor(0.0f, 1.0f).position(coordenadasPrincipal).title(""));

        // NOS MOVEMOS A LAS COORDENADAS PRINCIPAL DE LA ZONA...
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadasPrincipal));

        // ANIMACION DE ZOOM DE ENTRADA DE GOOGLE MAPS...
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadasPrincipal, 13f), 4000, null);
    }

    // UBICACIONES DE CALAKMUL....
    private void ubicacionCalakmul(double latitude, double longitude) {

        // CREAMOS EL MARCADOR PRICIPAL O RUTA INICIAL DONDE SE VA A UBICAR....
        LatLng coordenadasPrincipal = new LatLng(latitude, longitude);

        // CREAMOS UN ICONO PERSONALIZADO PARA VISUALIZAR EN EL MAPS....
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.pir2)).anchor(0.0f, 1.0f).position(coordenadasPrincipal).title("Calakmul"));

        // NOS MOVEMOS A LAS COORDENADAS PRINCIPAL DE LA ZONA...
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadasPrincipal));

        // ANIMACION DE ZOOM DE ENTRADA DE GOOGLE MAPS...
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadasPrincipal, 13f), 4000, null);
    }

    private void ubicacionZonaBecán(double latitude, double longitude) {

        // CREAMOS EL MARCADOR PRICIPAL O RUTA INICIAL DONDE SE VA A UBICAR....
        LatLng coordenadasPrincipal = new LatLng(latitude, longitude);

        // CREAMOS UN ICONO PERSONALIZADO PARA VISUALIZAR EN EL MAPS....
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.pir2)).anchor(0.0f, 1.0f).position(coordenadasPrincipal).title("Zona Arqueológica de Becán"));

        // NOS MOVEMOS A LAS COORDENADAS PRINCIPAL DE LA ZONA...
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadasPrincipal));

        // ANIMACION DE ZOOM DE ENTRADA DE GOOGLE MAPS...
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadasPrincipal, 13f), 4000, null);
    }

    private void ubicacionZonaXpuhil(double latitude, double longitude) {

        // CREAMOS EL MARCADOR PRICIPAL O RUTA INICIAL DONDE SE VA A UBICAR....
        LatLng coordenadasPrincipal = new LatLng(latitude, longitude);

        // CREAMOS UN ICONO PERSONALIZADO PARA VISUALIZAR EN EL MAPS....
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.pir2)).anchor(0.0f, 1.0f).position(coordenadasPrincipal).title("Zona Arqueológica Xpuhil"));

        // NOS MOVEMOS A LAS COORDENADAS PRINCIPAL DE LA ZONA...
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadasPrincipal));

        // ANIMACION DE ZOOM DE ENTRADA DE GOOGLE MAPS...
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadasPrincipal, 13f), 4000, null);
    }

    private void ubicacionZonaBalamkú(double latitude, double longitude) {

        // CREAMOS EL MARCADOR PRICIPAL O RUTA INICIAL DONDE SE VA A UBICAR....
        LatLng coordenadasPrincipal = new LatLng(latitude, longitude);

        // CREAMOS UN ICONO PERSONALIZADO PARA VISUALIZAR EN EL MAPS....
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.pir2)).anchor(0.0f, 1.0f).position(coordenadasPrincipal).title("Zona Arqueológica de Balamkú"));

        // NOS MOVEMOS A LAS COORDENADAS PRINCIPAL DE LA ZONA...
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadasPrincipal));

        // ANIMACION DE ZOOM DE ENTRADA DE GOOGLE MAPS...
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadasPrincipal, 13f), 4000, null);
    }

    private void ubicacionZonaHormiguero(double latitude, double longitude) {

        // CREAMOS EL MARCADOR PRICIPAL O RUTA INICIAL DONDE SE VA A UBICAR....
        LatLng coordenadasPrincipal = new LatLng(latitude, longitude);

        // CREAMOS UN ICONO PERSONALIZADO PARA VISUALIZAR EN EL MAPS....
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.pir2)).anchor(0.0f, 1.0f).position(coordenadasPrincipal).title("Zona Arqueológica de Hormiguero"));

        // NOS MOVEMOS A LAS COORDENADAS PRINCIPAL DE LA ZONA...
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadasPrincipal));

        // ANIMACION DE ZOOM DE ENTRADA DE GOOGLE MAPS...
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadasPrincipal, 13f), 4000, null);
    }
}