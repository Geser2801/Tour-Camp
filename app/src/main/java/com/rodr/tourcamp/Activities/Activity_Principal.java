package com.rodr.tourcamp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rodr.tourcamp.R;
import com.rodr.tourcamp.Fragments.HelpFragment;
import com.rodr.tourcamp.Fragments.HomeFragment;
import com.rodr.tourcamp.Fragments.MapsFragment;
import com.rodr.tourcamp.Fragments.SettingsFragment;

public class Activity_Principal extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toobalPrincipal;

    BottomNavigationView btnNavegacion; // BARRA NAVEGACION VIEW INFERIOR....
    Window window; // OBJETO DE WINDOW LA CUAL ME PERMITE CAMBIAR LOS COLORES DE FONDO....

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        this.window = getWindow(); // INSTANCION MI OBJETO DE TIPO WINDOW....

        initGui();
        //barra();
        //soporteToolbar();
        launchHome();
        barraNavegacion();
    }

    private void initGui(){
        //toobalPrincipal = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_Principal);
        btnNavegacion = (BottomNavigationView) findViewById(R.id.navegacion);
    }

    private void barra(){
        btnNavegacion.setItemIconTintList(null);
    }

    private void soporteToolbar(){
        //setSupportActionBar(toobalPrincipal);
    }

    // METODO DONDE SE PUEDE SABER QUE OPCION SE ESTA PRECIONANDO LA BARRA DE NAVEGACION INFERIOR...
    private void barraNavegacion(){
        btnNavegacion.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_Home:
                        launchHome();
                        return true;
                    case R.id.menu_Maps:
                        launchMaps();
                        return true;
                    case R.id.menu_Settings:
                        launchSettings();
                        return true;
                    case R.id.menu_Help:
                        launchHelp();
                        return true;
                    default:
                }
                return false;
            }
        });
    }

    // #34495E COLOR TOOLBAR AND NAVIGATION BAR....

    // METODO DE LANZAR FRAGMENT HOME.....
    private void launchHome(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HomeFragment home = new HomeFragment();
        transaction.replace(R.id.visor, home).commit();
        String background = "#F9E79F";
        //window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(background)));
        window.setBackgroundDrawableResource(R.drawable.background_home_fragment);

    }

    // METODO DE LANZAR FRAGMENT MAPS.....
    private void launchMaps(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapsFragment maps = new MapsFragment();
        transaction.replace(R.id.visor, maps).commit();
        String toolbar = "#797D7F";
        String background = "#839192";
        String navigationbar = "#797D7F";
        cambiaColorToobarAndNavigationBar(toolbar, background, navigationbar);
        //window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(background)));
    }

    // METODO DE LANZAR FRAGMENT SETTINGS.....
    private void launchSettings(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SettingsFragment settings = new SettingsFragment();
        transaction.replace(R.id.visor, settings).commit();
        String toolbar = "#34495E";
        String background = "#273746";
        String navigationbar = "#34495E";
        cambiaColorToobarAndNavigationBar(toolbar, background, navigationbar);
        //window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(background)));
    }

    // METODO DE LANZAR FRAGMENT HELP.....
    private void launchHelp(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HelpFragment help = new HelpFragment();
        transaction.replace(R.id.visor, help).commit();
        String toolbar = "#34495E";
        String background = "#273746";
        String navigationbar = "#34495E";
        cambiaColorToobarAndNavigationBar(toolbar, background, navigationbar);
        //window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(background)));
    }

    // METODO DE CAMBIO DE COLORES DE FONDO, BARRA DE NOTIFICACION Y BARRA DE NAVEGACION...
    private void cambiaColorToobarAndNavigationBar(String primaryToolbar, String primaryBackgroud, String primaryNavigationBar){
        // COLOR primaryToolbar.....
        window.setStatusBarColor(Color.parseColor(primaryToolbar));

        // COLOR primaryBackgroud.....
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(primaryBackgroud)));

        // COLOR primaryNavigationBar.....
        window.setNavigationBarColor(Color.parseColor(primaryNavigationBar));
    }
}