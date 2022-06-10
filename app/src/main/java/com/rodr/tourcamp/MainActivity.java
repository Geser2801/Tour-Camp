package com.rodr.tourcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rodr.tourcamp.Activities.Activity_Principal;
import com.rodr.tourcamp.Activities.Login_Activity;

public class MainActivity extends AppCompatActivity {

    Window window; // OBJETO DE WINDOW LA CUAL ME PERMITE CAMBIAR LOS COLORES DE FONDO....
    TextView txtFrom, txtBeta;
    ImageView imgSplashScreen;

    // VARIABLES DE ANIMACIONES...
    Animation animacionDesplazamientoArriba, animacionDesplazamientoAbajo, animacionDesplazamientoIzquierda, animacionDesplazamientoDerecha, animacionZoomEntrada, animacionZoomSalida;

    // Obtén el usuario con sesión activa....
    /*
        La manera recomendada de hacerlo es llamar al método getCurrentUser.
        Si no hay un usuario que haya accedido, getCurrentUser muestra un valor nulo:
    */
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //animacionLanzarLogin();
        inicializarComponentesAndroid();
        initGui();
        animacionSplash();
    }

    private void inicializarComponentesAndroid(){
        this.window = getWindow(); // INSTANCION MI OBJETO DE TIPO WINDOW....
    }

    /*private void animacionLanzarLogin(){
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 5000);
    }*/

    private void initGui(){
        txtFrom = (TextView) findViewById(R.id.txt_From);
        txtBeta = (TextView) findViewById(R.id.txt_Beta);
        imgSplashScreen = (ImageView) findViewById(R.id.img_SplashScreen);

    }

    // METODO DE ANIMACIONES DE PANTALLA Y SPLASH....
    private void animacionSplash(){
        animacionDesplazamientoArriba = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_arriba);
        animacionDesplazamientoAbajo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_abajo);
        animacionDesplazamientoDerecha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_derecha);
        animacionDesplazamientoIzquierda = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_izquierda);
        animacionZoomEntrada = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_zoom_entrada);
        animacionZoomSalida = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_zoom_salida);

        txtFrom.setAnimation(animacionDesplazamientoAbajo);
        txtBeta.setAnimation(animacionDesplazamientoAbajo);
        imgSplashScreen.setAnimation(animacionZoomEntrada);

        String toolbar = "#ABEBC6";
        String navigationBar = "#ABEBC6";

        // COLOR primaryToolbar.....
        window.setStatusBarColor(Color.parseColor(toolbar));

        // Navigation....
        window.setNavigationBarColor(Color.parseColor(navigationBar));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user != null) { // SI HAY USUARIO INICIADO ENTRAR A LA ACTIVITY PRINCIPAL...
                    Intent intent = new Intent(getApplicationContext(), Activity_Principal.class);
                    startActivity(intent);
                    finish();
                }
                else { // CASO CONTRARIO LOGUEARSE....
                    Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },1000); // TIEMPO DE SPLASH...
    }
}