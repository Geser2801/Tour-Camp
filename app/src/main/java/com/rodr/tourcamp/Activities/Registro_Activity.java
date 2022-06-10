package com.rodr.tourcamp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rodr.tourcamp.R;
import com.rodr.tourcamp.SQLite.AdminDB;
import com.rodr.tourcamp.Contexto.AppFragments;

import java.util.HashMap;
import java.util.Map;

public class Registro_Activity extends AppCompatActivity {

    Window window; // OBJETO DE WINDOW LA CUAL ME PERMITE CAMBIAR LOS COLORES DE FONDO....
    ImageView imgLogoRegistro;
    EditText txtNombreRegistro, txtApellidoPRegistro, txtApellidoMRegistro, txtEmailRegistro, txtContraseniaRegistro, txtConfirmarContraseniaRegistro;
    TextView txtTituloRegistro, txtGeneroRegistro, txtTermCondiRegistro;
    RadioGroup rbGroudGeneroRegistro;
    RadioButton rbMasculinoRegistro, rbFemeninoRegistro;
    Button btnRegistrarseRegistro, btnRegresarLogin;

    // VARIABLES DE ANIMACIONES...
    Animation animacionDesplazamientoArriba, animacionDesplazamientoAbajo, animacionDesplazamientoIzquierda, animacionDesplazamientoDerecha, animacionZoomEntrada, animacionZoomSalida;

    String genero = ""; // VARIABLE PARA GUARDAR EL GENERO SEGUN SEA SELECCIONADO EN RADIOBUTTON....

    // DECLARAR UNA INSTANCIA DE FirebaseAuth....
    private FirebaseAuth mAuth;

    // DECLARAR UNA INSTANCIA DE FirebaseFirestore....
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializarComponentesFirebase();
        initGui();
        colorToolbarNavigationBar();
        animacionRegistro();
        registrarse();
        backLogin();
    }

    // INICIALIZO MIS COMPONENTES DE FIREBASE....
    private void inicializarComponentesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        // When initializing your Activity, check to see if the user is currently signed in.....
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    /*
        Create a new createAccount method which takes in an email address and password, validates
        them and then creates a new user with the createUserWithEmailAndPassword method.....
    */

    /*public void registrarUsuraios(View view){
        if (txtContraseniaRegistro.getText().toString().equals(txtConfirmarContraseniaRegistro.getText().toString())){
            mAuth.createUserWithEmailAndPassword(txtEmailRegistro.getText().toString(), txtContraseniaRegistro.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                // Log.d(TAG, "createUserWithEmail:success");
                                Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                                //startActivity(intent);
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
        else
            Toast.makeText(getApplicationContext(), "Las contraseñas no coiciden", Toast.LENGTH_SHORT).show();

    }*/

    private void initGui(){
        imgLogoRegistro = (ImageView) findViewById(R.id.img_Logo_Registro);
        txtNombreRegistro = (EditText) findViewById(R.id.txt_Nombre_Registro);
        txtApellidoPRegistro = (EditText) findViewById(R.id.txt_ApellidoP_Registro);
        txtApellidoMRegistro = (EditText) findViewById(R.id.txt_ApellidoM_Registro);
        txtEmailRegistro = (EditText) findViewById(R.id.txt_Email_Registro);
        txtContraseniaRegistro = (EditText) findViewById(R.id.txt_Contrasenia_Registro);
        txtConfirmarContraseniaRegistro = (EditText) findViewById(R.id.txt_Confirmar_Contrasenia_Registro);
        txtTituloRegistro = (TextView) findViewById(R.id.txt_Titulo_Registro);
        txtGeneroRegistro = (TextView) findViewById(R.id.txt_Genero_Registro);
        txtTermCondiRegistro = (TextView) findViewById(R.id.txt_Term_Condi_Registro);
        rbGroudGeneroRegistro = (RadioGroup) findViewById(R.id.rb_Groud_Genero_Registro);
        rbMasculinoRegistro = (RadioButton) findViewById(R.id.rb_Masculino_Registro);
        rbFemeninoRegistro = (RadioButton) findViewById(R.id.rb_Femenino_Registro);
        btnRegistrarseRegistro = (Button) findViewById(R.id.btn_Registrarse_Registro);
        btnRegresarLogin = (Button) findViewById(R.id.btn_Regresar_Login);
    }

    // METODO DE ANIMACIONES DE PANTALLA....
    private void animacionRegistro(){

        // INSTANCIAMIENTO DE LAS ANIMACIONES CON EL XML....
        animacionDesplazamientoArriba = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_arriba);
        animacionDesplazamientoAbajo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_abajo);
        animacionDesplazamientoDerecha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_derecha);
        animacionDesplazamientoIzquierda = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_izquierda);
        animacionZoomEntrada = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_zoom_entrada);
        animacionZoomSalida = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_zoom_salida);

        // APLICAR LAS ANIMACIONES A TODOS LOS CONTROLADORES O CONTROLES QUE TIENE ESTA ACTIVIDAD....
        imgLogoRegistro.setAnimation(animacionDesplazamientoIzquierda);
        txtTituloRegistro.setAnimation(animacionDesplazamientoDerecha);
        txtNombreRegistro.setAnimation(animacionDesplazamientoAbajo);
        txtApellidoPRegistro.setAnimation(animacionDesplazamientoAbajo);
        txtApellidoMRegistro.setAnimation(animacionDesplazamientoAbajo);
        txtGeneroRegistro.setAnimation(animacionDesplazamientoDerecha);
        rbGroudGeneroRegistro.setAnimation(animacionDesplazamientoIzquierda);
        txtEmailRegistro.setAnimation(animacionDesplazamientoAbajo);
        txtContraseniaRegistro.setAnimation(animacionDesplazamientoAbajo);
        txtConfirmarContraseniaRegistro.setAnimation(animacionDesplazamientoAbajo);
        btnRegistrarseRegistro.setAnimation(animacionDesplazamientoAbajo);
        txtTermCondiRegistro.setAnimation(animacionDesplazamientoAbajo);
    }

    // METODO DE REGISTRARSE (LLENAR CAMPOS)....
    private void registrarse(){
        btnRegistrarseRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCamposRegistro(); // VALIDAR MIS CAMPOS DE REGISTRO E IR AL LOGUEO DESPUES.....
            }
        });
    }

    // METODO DE RETROCESO HACIA EL LOGIN...
    private void backLogin(){
        btnRegresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // METODO DE VALIDACION DE CAMPOS DE REGISTRO....
    private void validarCamposRegistro(){
        /*String fraseNombre, fraseApellidoP, fraseApellidoM, fraseEmail, frasePassword, frasePassConfirm;
        String strPass = txtContraseniaRegistro.getText().toString();
        String strPassConfirm = txtConfirmarContraseniaRegistro.getText().toString();

        fraseNombre = txtNombreRegistro.getText().toString();
        fraseApellidoP = txtApellidoPRegistro.getText().toString();
        fraseApellidoM = txtApellidoMRegistro.getText().toString();
        fraseEmail = txtEmailRegistro.getText().toString();
        frasePassword = txtContraseniaRegistro.getText().toString();
        frasePassConfirm = txtConfirmarContraseniaRegistro.getText().toString();


        //group.getCheckedRadioButtonId()<=0
        // rbMasculinoRegistro.isChecked() || rbFemeninoRegistro.isChecked()

        if (fraseNombre.isEmpty())
            txtNombreRegistro.setError("Introduzca un nombre");
        if (fraseApellidoP.isEmpty())
            txtApellidoPRegistro.setError("Agregue su Apellido Paterno");
        if (fraseApellidoM.isEmpty())
            txtApellidoMRegistro.setError("Agregue su Apellido Materno");
        if (rbMasculinoRegistro.isChecked() || rbFemeninoRegistro.isChecked()) {
            //Toast.makeText(getApplication(), "Seleccione el genero", Toast.LENGTH_SHORT).show();
            rbFemeninoRegistro.setError(null);
        } else {
            // Toast.makeText(getApplication(), "Seleccione el genero", Toast.LENGTH_SHORT).show();
            rbFemeninoRegistro.setError("Seleccione el genero");
        }
        if (fraseEmail.isEmpty())
            txtEmailRegistro.setError("Introduzca su correo");
        if (frasePassword.isEmpty())
            txtContraseniaRegistro.setError("Introduzca su contraseña");
        if (frasePassConfirm.isEmpty())
            txtConfirmarContraseniaRegistro.setError("Confirme su contraseña");
        else {
            if (Patterns.EMAIL_ADDRESS.matcher(fraseEmail).matches()){
                if (strPass.equals(strPassConfirm)) {
                    Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    txtContraseniaRegistro.setError("Contraseña no compatibles");
                    txtConfirmarContraseniaRegistro.setError("Contraseña no compatibles");
                }
            } else {
                //Toast.makeText(getApplication(), "Formato de correo electronico", Toast.LENGTH_SHORT).show();
                txtEmailRegistro.setError("Correo no valido");
            }
        }*/

        if (txtNombreRegistro.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtNombreRegistro.setError("Introduzca un nombre"); // MANDAR UN MENSAJE DE ERROR....
        if (txtApellidoPRegistro.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtApellidoPRegistro.setError("Agregue su Apellido Paterno"); // MANDAR UN MENSAJE DE ERROR....
        if (txtApellidoMRegistro.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtApellidoMRegistro.setError("Agregue su Apellido Materno"); // MANDAR UN MENSAJE DE ERROR....
        if (rbMasculinoRegistro.isChecked() || rbFemeninoRegistro.isChecked()) { // SI ESTA PULSADO UNO DE LOS 2 RADIOS...
            //Toast.makeText(getApplication(), "Seleccione el genero", Toast.LENGTH_SHORT).show();
            rbFemeninoRegistro.setError(null);
        } else { // CASO DE QUE NO ESTE PULSADO UNOS DE LOS 2...
            // Toast.makeText(getApplication(), "Seleccione el genero", Toast.LENGTH_SHORT).show();
            rbFemeninoRegistro.setError("Seleccione el genero"); // MANDAR UN MENSAJE DE ERROR....
        }

        if (rbMasculinoRegistro.isChecked()) // SI ESTA PULSADO LA OPCION MASCULINO...
            genero = "Masculino"; // LA VARIABLE genero TOMA EL VALOR DE MASCULINO..
        else // CASO CONTRARIO....
            genero = "Femenino"; // LA VARIABLE genero TOMA EL VALOR DE FEMENINO..

        if (txtEmailRegistro.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtEmailRegistro.setError("Introduzca su correo"); // MANDAR UN MENSAJE DE ERROR....
        if (txtContraseniaRegistro.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtContraseniaRegistro.setError("Introduzca su contraseña"); // MANDAR UN MENSAJE DE ERROR....
        if (txtConfirmarContraseniaRegistro.getText().toString().length() == 0 ) // SI EL CAMPO ESTA VACIO....
            txtConfirmarContraseniaRegistro.setError("Confirme su contraseña"); // MANDAR UN MENSAJE DE ERROR....
        else {
            if (Patterns.EMAIL_ADDRESS.matcher(txtEmailRegistro.getText().toString()).matches()){ // SI EL CORREO ESTA FORMATO DE EXAMPLE@GAMIL.COM = TRUE....
                if (txtContraseniaRegistro.getText().toString().length() < 8) // SI LA COMTRASEÑA NO ES MAS DE 8 CARACTERES....
                    txtContraseniaRegistro.setError("La contraseña debe ser mas de 8 caracteres"); // MANDAR UN MENSAJE DE ERROR....
                else { // CASO CONTRARIO DE SER MAS DE 8 CARACTERES....
                    if (txtContraseniaRegistro.getText().toString().equals(txtConfirmarContraseniaRegistro.getText().toString())) { // SI LA CONTRASEÑA ES IGUAL A CONFIRMAR CONTRASEÑA....
                       /*mAuth.createUserWithEmailAndPassword(txtEmailRegistro.getText().toString(), txtContraseniaRegistro.getText().toString())
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                                            startActivity(intent);
                                            finish();
                                            //updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                            //updateUI(null);
                                        }

                                        // ...
                                    }
                                });*/
                        /*mAuth.createUserWithEmailAndPassword(txtEmailRegistro.getText().toString(), txtContraseniaRegistro.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Map<String, Object> mapUser = new HashMap<>();
                                String id = mAuth.getCurrentUser().getUid();
                                //mapUser.put("id", id);
                                mapUser.put("Nombre", txtNombreRegistro.getText().toString());
                                mapUser.put("Apellido Paterno", txtApellidoPRegistro.getText().toString());
                                mapUser.put("Apellido Materno", txtApellidoMRegistro.getText().toString());
                                mapUser.put("Genero", genero);
                                mapUser.put("Correo", txtEmailRegistro.getText().toString());
                                mapUser.put("Contraseña", txtContraseniaRegistro.getText().toString());
                                mapUser.put("Confirmar Contraseña", txtConfirmarContraseniaRegistro.getText().toString());

                                db.collection("Usuarios").document(txtEmailRegistro.getText().toString()).set(mapUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error failed.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }); */

                        /*
                            CUANDO UN USUARIO NUEVO SE REGISTRE MEDIANTE EL FORMULARIO DE REGISTRO DE LA APP..
                            METODO DE AUTENTICACION DE FIREBASEAUTH, LA CUAL PARA CREAR UNA CUENTA, SE PASA COMO
                            ARGUMENTO LA DIRECCION DE CORREO ELECTRONICO Y CONTRASEÑA DEL USUARIO
                            AL METODO createUserWithEmailAndPassword.
                        */
                        mAuth.createUserWithEmailAndPassword(txtEmailRegistro.getText().toString(), txtContraseniaRegistro.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) { // TASK = TAREA....
                                FirebaseUser user = mAuth.getCurrentUser();

                                /*
                                    MAPEAMOS LOS DATOS PARA ALMECENARLOS EN LA BD DE FIREBASE....
                                    EL MAPEO FUNCIONA RECIBIENDO COMO ARGUMENTO DOS PARAMETROS LA CUAL SON,
                                    MAP(CLAVE, VALOR).
                                */

                                Map<String, Object> mapUser = new HashMap<>(); // OBJETO O VARIABLE DE MAPEO...
                                //String id = mAuth.getCurrentUser().getUid(); // OBTENER EL ID DEL USUARIO...
                                //mapUser.put("id", id);
                                mapUser.put("Nombre", txtNombreRegistro.getText().toString()); // MANDAMOS EL "NOMBRE" A LA COLECCION PARA REGISTRARSE EN FIREBASE... LO QUE CONTENGA LA CAJA DE TEXTO...
                                mapUser.put("Apellido Paterno", txtApellidoPRegistro.getText().toString()); // MANDAMOS EL "APELLIDO PATERNO" A LA COLECCION PARA REGISTRARSE EN FIREBASE... LO QUE CONTENGA LA CAJA DE TEXTO...
                                mapUser.put("Apellido Materno", txtApellidoMRegistro.getText().toString()); // MANDAMOS EL "APELLIDO MATERNO" A LA COLECCION PARA REGISTRARSE EN FIREBASE... LO QUE CONTENGA LA CAJA DE TEXTO...
                                mapUser.put("Genero", genero); // MANDAMOS EL "GENERO" A LA COLECCION PARA REGISTRARSE EN FIREBASE... LO QUE CONTENGA LA CAJA DE TEXTO...
                                mapUser.put("Correo", txtEmailRegistro.getText().toString()); // MANDAMOS EL "CORREO" A LA COLECCION PARA REGISTRARSE EN FIREBASE... LO QUE CONTENGA LA CAJA DE TEXTO...
                                mapUser.put("Contraseña", txtContraseniaRegistro.getText().toString()); // // MANDAMOS LA "CONTRASEÑA" A LA COLECCION PARA REGISTRARSE EN FIREBASE... LO QUE CONTENGA LA CAJA DE TEXTO...
                                mapUser.put("Confirmar Contraseña", txtConfirmarContraseniaRegistro.getText().toString()); // MANDAMOS "CONFIRMAR CONTRASEÑA" A LA COLECCION PARA REGISTRARSE EN FIREBASE... LO QUE CONTENGA LA CAJA DE TEXTO...

                                // BD LOCAL...
                                int version = AppFragments.version; // VERSION DE BD....
                                String dbName = AppFragments.databaseNameTourCamp; // NOMBRE DE LA BD..
                                String tableName = AppFragments.tbUsuarios; // NOMBRE DE TABLA DE LA BD....

                                // INSTANCIA DE LA CLASE....
                                AdminDB adminDB = new AdminDB(getApplicationContext(), dbName, null, version);

                                // PARA ESCRIBIR EN LA BD...
                                SQLiteDatabase baseDatos = adminDB.getWritableDatabase();

                                if (baseDatos != null) { // SI LA BD NO EXISTE, CREARLA...
                                    try {
                                        // INSERTAR LOS DATOS EN LA BD LOCAL...
                                        ContentValues registro = new ContentValues();
                                        registro.put(AppFragments.tbCorreoUser, txtEmailRegistro.getText().toString());
                                        registro.put(AppFragments.tbNombreUser, txtNombreRegistro.getText().toString());
                                        registro.put(AppFragments.tbApellidoPaternoUser, txtApellidoPRegistro.getText().toString());
                                        registro.put(AppFragments.tbApellidoMaternoUser, txtApellidoMRegistro.getText().toString());
                                        registro.put(AppFragments.tbGeneroUser, genero);
                                        registro.put(AppFragments.tbContraseniaUser, txtContraseniaRegistro.getText().toString());
                                        registro.put(AppFragments.tbConfirmarContraseñaUser, txtConfirmarContraseniaRegistro.getText().toString());

                                        baseDatos.insert(tableName, null, registro); // EJECUTAR LA INSERCION...
                                        baseDatos.close(); // CERRAMOS LA BD...
                                    } catch (Exception e){
                                        Toast.makeText(getApplicationContext(),"Error ",Toast.LENGTH_SHORT).show(); // ERROR SI NO SE INSERTA...
                                    }
                                }

                                // INSERCION DE DATOS DE EN FIREBASE...
                                /*
                                    Cloud Firestore ALMACENA DATOS EN DOCUMENTOS (NOMBRE BASE DE DATOS),
                                    QUE SE ALMACENAN EN COLECCIONES (TABLAS).
                                    Cloud Firestore CREA COLECCIONES Y DOCUMENTOS IMPLICITAMENTE LA 1RA VES
                                    QUE SE AGREGA DATOS AL DOCUMENTO.
                                */

                                db.collection("Usuarios").document(txtEmailRegistro.getText().toString()).set(mapUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(), Activity_Principal.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error failed.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        txtContraseniaRegistro.setError("Contraseña no compatibles"); // CASO DE QUE LAS CONTRASEÑAS SEAN DISTINTAS, MANDAR MENSAJE DE ERROR...
                        txtConfirmarContraseniaRegistro.setError("Contraseña no compatibles"); // CASO DE QUE LAS CONTRASEÑAS SEAN DISTINTAS, MANDAR MENSAJE DE ERROR...
                    }
                }
            } else {
                //Toast.makeText(getApplication(), "Formato de correo electronico", Toast.LENGTH_SHORT).show();
                txtEmailRegistro.setError("Correo no valido"); // CASO CONTRARIO DE QUE EL FORMATO DE CORREO NO SEA CORRECTO, MANDAR UN MENSAJE DE ERROR....
            }
        }
    }

    // METODO LA CUAL CAMBIA DE COLOR MI BARRA DE NOTIFICACION Y NAVEGACION....
    private void colorToolbarNavigationBar(){
        this.window = getWindow(); // INSTANCION MI OBJETO DE TIPO WINDOW....
        String toolbar = "#A47047";
        String navigationBar = "#A47047";

        // COLOR primaryToolbar.....
        window.setStatusBarColor(Color.parseColor(toolbar));

        // Navigation....
        window.setNavigationBarColor(Color.parseColor(navigationBar));
    }
}