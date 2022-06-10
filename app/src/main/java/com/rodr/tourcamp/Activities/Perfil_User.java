package com.rodr.tourcamp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.rodr.tourcamp.R;
import com.rodr.tourcamp.SQLite.AdminDB;
import com.rodr.tourcamp.Contexto.AppFragments;

import java.util.HashMap;
import java.util.Map;

public class Perfil_User extends AppCompatActivity {

    String emaill = "";
    String [] datos = new String[7];
    Button btnCerrarSesion, btnEditar, btnGuardarDatosUser;
    EditText txtNombrePerfil, txtApellidoPPerfil, txtApellidoMPerfil, txtGeneroPerfil, txtEmailPerfil;
    String pass, confPass;
    ImageButton btnEditarPerfil;


    // DECLARAR UNA INSTANCIA DE FirebaseUser....
    FirebaseUser user;

    // DECLARAR UNA INSTANCIA DE FirebaseFirestore....
    FirebaseFirestore db;

    // DECLARAR UNA INSTANCIA DE FirebaseAuth....
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);
        inicializarComponentesFirebase();
        initGui();
        siHayUsuarioIniciadoSesion();
        modificarDatosUser();
        cerrarSesion();

    }

    private void initGui(){
        btnCerrarSesion = (Button) findViewById(R.id.btn_Cerrar_Sesion);
        //btnEditar = (Button) findViewById(R.id.btn_Editar);
        btnGuardarDatosUser = (Button) findViewById(R.id.btn_Guardar_Datos_User);
        txtNombrePerfil = (EditText) findViewById(R.id.txt_Nombre_Perfil);
        txtApellidoPPerfil = (EditText) findViewById(R.id.txt_ApellidoP_Perfil);
        txtApellidoMPerfil = (EditText) findViewById(R.id.txt_ApellidoM_Perfil);
        txtGeneroPerfil = (EditText) findViewById(R.id.txt_Genero_Perfil);
        txtEmailPerfil = (EditText) findViewById(R.id.txt_Email_Perfil);
        btnEditarPerfil = (ImageButton) findViewById(R.id.btn_Editar_Perfil);
    }

    // INICIALIZO MIS COMPONENTES DE FIREBASE....
    private void inicializarComponentesFirebase(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    // METODO QUE PERMITE IDENTIFICAR SI HAY USUARIO EXISTENTE...
    private void siHayUsuarioIniciadoSesion(){

        //obtenerDatosEnBdLocal();
        obtenerDatosFirebase(); // OBTENER LOS DATOS O RECUPERARLOS EN LA BD FIREBASE....

        // Obtén el usuario con sesión activa....
        /*
            La manera recomendada de hacerlo es llamar al método getCurrentUser.
            Si no hay un usuario que haya accedido, getCurrentUser muestra un valor nulo:
        */
        if (user != null) {
            // User is signed in
            //txtNombrePerfil.setText(user.getEmail());
        } else {
            // No user is signed in
        }
    }

    private void obtenerDatosEnBdLocal() {
        int version = AppFragments.version;
        String dbName = AppFragments.databaseNameTourCamp;
        String tableName = AppFragments.tbUsuarios;

        AdminDB adminDB = new AdminDB(getApplicationContext(), dbName, null, version);
        SQLiteDatabase baseDatos = adminDB.getWritableDatabase();

        String consulta = "SELECT * FROM " + tableName;
        Cursor cursor = baseDatos.rawQuery(consulta,null);

        while (cursor.moveToNext()){
            emaill = cursor.getString(0).toString();

        }

        db.collection("Usuarios").document(emaill).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot i) {
                txtNombrePerfil.setText(i.get("Nombre").toString());
                txtApellidoPPerfil.setText(i.get("Apellido Paterno").toString());
                txtApellidoMPerfil.setText(i.get("Apellido Materno").toString());
                txtGeneroPerfil.setText(i.get("Genero").toString());
                txtEmailPerfil.setText(i.get("Correo").toString());
                pass = i.get("Contraseña").toString();
                confPass = i.get("Confirmar Contraseña").toString();

                txtNombrePerfil.setEnabled(false);
                txtApellidoPPerfil.setEnabled(false);
                txtApellidoMPerfil.setEnabled(false);
                txtGeneroPerfil.setEnabled(false);
                txtEmailPerfil.setEnabled(false);
                btnGuardarDatosUser.setEnabled(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "No se pudo obtener los datos.", Toast.LENGTH_SHORT).show();
            }
        });


        /*AdminDB adminDB = new AdminDB(getApplicationContext(), "TourCamp", null, 1);
        SQLiteDatabase baseDatos = adminDB.getWritableDatabase();

        String consulta = "SELECT * FROM Usuarios ";
        Cursor cursor = baseDatos.rawQuery(consulta,null);

        while (cursor.moveToNext()){
            txtEmailPerfil.setText(cursor.getString(0).toString());
            txtNombrePerfil.setText(cursor.getString(1).toString());
            txtApellidoPPerfil.setText(cursor.getString(2).toString());
            txtApellidoMPerfil.setText(cursor.getString(3).toString());
            txtGeneroPerfil.setText(cursor.getString(4).toString());
        }*/
    }

    // RECUPERAR LOS DATOS EN FIREBASE...
    private void obtenerDatosFirebase(){

        // AL INICIAR LAS CAJAS DE TEXTOS SE INHABILITAN....
        txtNombrePerfil.setEnabled(false);
        txtApellidoPPerfil.setEnabled(false);
        txtApellidoMPerfil.setEnabled(false);
        txtGeneroPerfil.setEnabled(false);
        txtEmailPerfil.setEnabled(false);

        // EL BOTON DE GUARDAR SE OCULTA O QUEDA INVISIBLE (HIDDE)....
        btnGuardarDatosUser.setVisibility(View.GONE);

        String id = mAuth.getCurrentUser().getUid(); // OBTENER ID DEL USUARIO EN LA COLECCION....
        String correo = user.getEmail(); // OBTENER EL CORREO DEL USUARIO EN LA COLECCION.

        // ENTRO EN EL DOCUMENTO (BASE DE DATOS), Y BUSCA LA COLECCION (TABLA) "Usuarios" Y ESPECIFICO QUE LO BUSQUE MEDIANTE EL CORREO...
        DocumentReference documentReference = db.collection("Usuarios").document(correo);

        /*
            OBTENEMOS SU METODO O EVENTO....
            ESTE METODO ME PERMITE RECUPERAR LA INFORMACION DEL USUARIO Y TRAERMELOS,
            EN ESTE CASO SE LOS ASIGNO A LOS EDITHTEXT DE CADA UNO DE ELLOS
        */
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                txtNombrePerfil.setText(value.getString("Nombre")); // RECUPERO EL NOMBRE, Y SE LAMDO A LA CAJA DE TEXTO, VISUALIZA EL NOMBRE...
                txtApellidoPPerfil.setText(value.getString("Apellido Paterno")); // RECUPERO EL APELLIDO PATERNO, Y SE LAMDO A LA CAJA DE TEXTO, VISUALIZA EL APELLIDO P...
                txtApellidoMPerfil.setText(value.getString("Apellido Materno")); // RECUPERO EL APELLIDO MATERNO, Y SE LAMDO A LA CAJA DE TEXTO, VISUALIZA EL APELLIDO M...
                txtGeneroPerfil.setText(value.getString("Genero")); // RECUPERO EL GENERO, Y SE LAMDO A LA CAJA DE TEXTO, VISUALIZA EL GENERO...
                txtEmailPerfil.setText(value.getString("Correo")); // RECUPERO EL CORREO, Y SE LAMDO A LA CAJA DE TEXTO, VISUALIZA EL CORREO...
                pass = value.getString("Contraseña"); // RECUPERO LA CONTRASEÑA.....
                confPass = value.getString("Confirmar Contraseña"); // RECUPERO CONFIRMAR CONTRASEÑA.....
            }
        });
    }

    // BOTON METODO DE CERRAR SESION...
    private void cerrarSesion(){
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    // BOTON METODO DE MODIFICAR LOS DATOS DEL USUARIO...
    private void modificarDatosUser(){

        // AL PULSAR EL BOTON DE EDITAR PERFIL
        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // LAS CAJAS DE TEXTOS SE HABILITAN...
                txtNombrePerfil.setEnabled(true);
                txtApellidoPPerfil.setEnabled(true);
                txtApellidoMPerfil.setEnabled(true);
                txtGeneroPerfil.setEnabled(true);
                txtEmailPerfil.setEnabled(false);

                // EL BOTON DE CERRAR SESION SE OCULTA O QUEDA INVISIBLE (HIDDE)....
                btnCerrarSesion.setVisibility(View.GONE);

                // SE HABILITA EL BOTON DE GUARDAR DATOS O MODIFICAR....
                btnGuardarDatosUser.setVisibility(View.VISIBLE);
            }
        });

        // EVENTO LISTENER DE GUARDAR DATOS...
        btnGuardarDatosUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                    MAPEAMOS LOS DATOS PARA ALMECENARLOS EN LA BD DE FIREBASE....
                    EL MAPEO FUNCIONA RECIBIENDO COMO ARGUMENTO DOS PARAMETROS LA CUAL SON,
                    MAP(CLAVE, VALOR).
                */
                Map<String, Object> mapUser = new HashMap<>(); // OBJETO O VARIABLE DE MAPEO...
                //String id = mAuth.getCurrentUser().getUid(); // OBTENER EL ID DEL USUARIO...
                //mapUser.put("id", id);
                mapUser.put("Nombre", txtNombrePerfil.getText().toString()); // MANDAMOS EL "NOMBRE" A LA COLECCION EN FIREBASE... LO QUE MOFICO EL USUARIO EN LA CAJA DE TEXTO...
                mapUser.put("Apellido Paterno", txtApellidoPPerfil.getText().toString()); // MANDAMOS EL "APELLIDO PATERNO" A LA COLECCION EN FIREBASE... LO QUE MOFICO EL USUARIO EN LA CAJA DE TEXTO...
                mapUser.put("Apellido Materno", txtApellidoMPerfil.getText().toString()); // MANDAMOS EL "APELLIDO MATERNO" A LA COLECCION EN FIREBASE... LO QUE MOFICO EL USUARIO EN LA CAJA DE TEXTO...
                mapUser.put("Genero", txtGeneroPerfil.getText().toString()); // MANDAMOS EL "GENERO" A LA COLECCION EN FIREBASE... LO QUE MOFICO EL USUARIO EN LA CAJA DE TEXTO...
                mapUser.put("Correo", txtEmailPerfil.getText().toString()); // // MANDAMOS EL "CORREO" A LA COLECCION EN FIREBASE...
                mapUser.put("Contraseña", pass); // MANDAMOS LA "CONTRASEÑA" A LA COLECCION EN FIREBASE...
                mapUser.put("Confirmar Contraseña", confPass); // MANDAMOS "CONFIRMAR CONTRASEÑA" A LA COLECCION EN FIREBASE...


                // INSERCION DE DATOS DE EN FIREBASE...
                /*
                    Cloud Firestore ALMACENA DATOS EN DOCUMENTOS (NOMBRE BASE DE DATOS),
                    QUE SE ALMACENAN EN COLECCIONES (TABLAS).
                    Cloud Firestore CREA COLECCIONES Y DOCUMENTOS IMPLICITAMENTE LA 1RA VES
                    QUE SE AGREGA DATOS AL DOCUMENTO.
                */
                db.collection("Usuarios").document(txtEmailPerfil.getText().toString()).set(mapUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // UNA VEZ MODIFICADO LOS CAMBIOS LAS CAJAS DE TEXTO SE INHABILITAN NUEVAMENTE...
                        txtNombrePerfil.setEnabled(false);
                        txtApellidoPPerfil.setEnabled(false);
                        txtApellidoMPerfil.setEnabled(false);
                        txtGeneroPerfil.setEnabled(false);
                        txtEmailPerfil.setEnabled(false);

                        // EL BOTON DE GUARDAR SE OCULTA (HIDDE)....
                        btnGuardarDatosUser.setVisibility(View.GONE);

                        // EL BOTON DE CERRAR SESION QUEDA VISIBLE....
                        btnCerrarSesion.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}