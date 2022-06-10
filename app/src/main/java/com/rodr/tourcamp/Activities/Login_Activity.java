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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rodr.tourcamp.R;
import com.rodr.tourcamp.SQLite.AdminDB;
import com.rodr.tourcamp.Contexto.AppFragments;

public class Login_Activity extends AppCompatActivity {

    Window window; // OBJETO DE WINDOW LA CUAL ME PERMITE CAMBIAR LOS COLORES DE FONDO....
    Button btnIniciarSesion;
    EditText txtEmail, txtPassword;
    ImageView imgLogoLogin;
    TextView txtRegistrate, txtTituloLogin, txtRegistrateAqui;

    // VARIABLES DE ANIMACIONES...
    Animation animacionDesplazamientoArriba, animacionDesplazamientoAbajo, animacionDesplazamientoIzquierda, animacionDesplazamientoDerecha;

    // DECLARAR UNA INSTANCIA DE FirebaseAuth....
    private FirebaseAuth mAuth;

    // VARIABLE LA CUAL ME PERMITIRA GUARDAR LOS DATOS EN UNA BD LOCAL TRAIDOS DESDE LA FIRABASE....
    String[] datos = new String[8];
    ///FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // DECLARAR UNA INSTANCIA DE FirebaseFirestore....
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initGui();
        colorToolbarNavigationBar();
        inicializarComponentesFirebase();
        lanzarActivityPrincipal();
        lanzarRegistrarse();
        animacionLogin();

    }

    private void initGui(){
        btnIniciarSesion = (Button) findViewById(R.id.btn_Iniciar_Sesion);
        txtEmail = (EditText) findViewById(R.id.txt_Email);
        txtPassword = (EditText) findViewById(R.id.txt_Password);
        imgLogoLogin = (ImageView) findViewById(R.id.img_Logo_Login);
        txtRegistrate = (TextView) findViewById(R.id.txt_Registrate);
        txtTituloLogin = (TextView) findViewById(R.id.txt_Titulo_Login);
        txtRegistrateAqui = (TextView) findViewById(R.id.txt_Registrate_Aqui);
    }

    // INICIALIZO MIS COMPONENTES DE FIREBASE....
    private void inicializarComponentesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    // METODO DE INICIAR SESION, (IR A LA PAGINA PRINCIPAL).....
    private void lanzarActivityPrincipal(){
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCamposLogin(); // VALIDAR MIS CAMPOS DE LOGUEO.....
            }
        });
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

    // METODO DE REGISTRARSE, (IR A LA PAGINA DE REGISTRAR USUARIO).....
    private void lanzarRegistrarse(){
        txtRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registro_Activity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    // METODO DE ANIMACIONES DE PANTALLA....
    private void animacionLogin(){

        // INSTANCIAMIENTO DE LAS ANIMACIONES CON EL XML....
        animacionDesplazamientoArriba = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_arriba);
        animacionDesplazamientoAbajo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_abajo);
        animacionDesplazamientoDerecha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_derecha);
        animacionDesplazamientoIzquierda = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacion_desplazamiento_izquierda);

        // APLICAR LAS ANIMACIONES A TODOS LOS CONTROLADORES O CONTROLES QUE TIENE ESTA ACTIVIDAD....
        txtTituloLogin.setAnimation(animacionDesplazamientoArriba);
        imgLogoLogin.setAnimation(animacionDesplazamientoArriba);
        txtEmail.setAnimation(animacionDesplazamientoDerecha);
        txtPassword.setAnimation(animacionDesplazamientoIzquierda);
        btnIniciarSesion.setAnimation(animacionDesplazamientoAbajo);
        txtRegistrateAqui.setAnimation(animacionDesplazamientoAbajo);
        txtRegistrate.setAnimation(animacionDesplazamientoAbajo);
    }

    // METODO DE VALIDACION DE CAMPOS DE LOGIN....
    private void validarCamposLogin(){
        /*if (txtEmail.getText().length()==0 || txtPassword.getText().length()==0)
                    Toast.makeText(getApplicationContext(), "Verifique los datos", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), Activity_Principal.class);
                    startActivity(intent);
                    finish();
                }*/

        // LANZAR O IR A LA PAGINA PRINCIPAL AL INICIAR SESION.....
        Intent intent = new Intent(getApplicationContext(), Activity_Principal.class);

        /*String fraseEmail = txtEmail.getText().toString();
        String frasePassword = txtPassword.getText().toString();

        if (fraseEmail.isEmpty())
            txtEmail.setError("Introduzca su correo");
        if (frasePassword.isEmpty())
            txtPassword.setError("Introduzca su contraseña");
        else {
            if (Patterns.EMAIL_ADDRESS.matcher(fraseEmail).matches()) {
                startActivity(intent);
                finish();
            } else {
                //Toast.makeText(getApplicationContext(), "Correo incorrecto", Toast.LENGTH_SHORT).show();
                txtEmail.setError("Correo no valido");
            }
        }*/

        if (txtEmail.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtEmail.setError("Introduzca su correo"); // MANDAR UN MENSAJE DE ERROR....
        if (txtPassword.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtPassword.setError("Introduzca su contraseña"); // MANDAR UN MENSAJE DE ERROR....
        else {
            if (Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches()) { // SI EL CORREO ESTA FORMATO DE EXAMPLE@GAMIL.COM = TRUE....
                if (txtPassword.getText().toString().length() < 8) // SI LA COMTRASEÑA NO ES MAS DE 8 CARACTERES....
                    txtPassword.setError("La contraseña debe ser mas de 8 caracteres"); // MANDAR UN MENSAJE DE ERROR....
                else { // CASO CONTRARIO DE SER MAS DE 8 CARACTERES....
                    /*mAuth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> taskAuth) {
                                    // ENTRAR EN LA COLECCION Y OBTENER LOS DATOS QUE TIENE....
                                    db.collection("Usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (taskAuth.isSuccessful()){ // SI LA TAREA SE CUMPLE....
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                // SE RECORRE EL DOCUMENTO DE LA BASE DE DATOS....
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    if (document.getId().equals(txtEmail.getText().toString())){ // OBTENGO EL ID DE LA COLECCION...
                                                        int i = 0; // CONTADOR DE DATOS DE LA COLECCION....
                                                        for (Object key: document.getData().values()) { RECORRER LOS DATOS QUE TIENE LA COLECCION (TABLAS)...
                                                            datos[i] = key.toString(); // TRAER LOS DATOS DE LA TABLAS Y ASIGNARLOS EN LA VARIABLE datos....
                                                            i++; INCREMENTAR EL CONTADOR...
                                                        }
                                                        registrarDatosLocal(); // ALMACENARLOS EN BD LOCAL LOS DATOS DE COLECION...
                                                    }
                                                    startActivity(intent);
                                                    finish();
                                                    Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                                                    //Log.d(TAG, document.getId() + " => " + document.getData());
                                                }
                                            } else
                                                Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });*/

                    /*
                        METODO DE AUTENTICACION DE FIREBASEAUTH, LA CUAL PARA INICIAR SESION, SE PASA COMO
                        ARGUMENTO LA DIRECCION DE CORREO ELECTRONICO Y CONTRASEÑA DEL USUARIO PREVIAMENTE
                        REGISTRADO AL METODO signInWithEmailAndPassword.
                    */
                    mAuth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) { // TASK = TAREA....
                                    if (task.isSuccessful()) { // SI LA TAREA SE CUMPLE, PASA A LA ACTIVIDAD PRINCIPAL, (PAGINA PRINCIPAL)....
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "signInWithEmail:success");
                                        Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show(); // MENSAJE DE EXITO....
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startActivity(intent);
                                        finish();
                                        //updateUI(user);
                                    } else { // CASO CONTRARIO DE QUE FALLE LA TAREA....
                                        // If sign in fails, display a message to the user.
                                        //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show(); // HA OCURRIDO UN ERROR...
                                        //updateUI(null);
                                    }
                                }
                            });
                }
            } else {
                //Toast.makeText(getApplicationContext(), "Correo incorrecto", Toast.LENGTH_SHORT).show();
                txtEmail.setError("Correo no valido"); // CASO CONTRARIO DE QUE EL FORMATO DE CORREO NO SEA CORRECTO, MANDAR UN MENSAJE DE ERROR....
            }
        }
    }

    public void registrarDatosLocal(){

        int version = AppFragments.version;
        String dbName = AppFragments.databaseNameTourCamp;
        String tableName = AppFragments.tbUsuarios;

        AdminDB adminDB = new AdminDB(getApplicationContext(), dbName, null, version);

        SQLiteDatabase baseDatos = adminDB.getWritableDatabase();

        if (baseDatos != null) {
            try {
                ContentValues registro = new ContentValues();
                registro.put("Correo", datos[1]);
                /*registro.put("Nombre", datos[0]);
                registro.put("Apellido_Paterno", datos[3]);
                registro.put("Apellido_Materno", datos[4]);
                registro.put("Genero", datos[5]);
                registro.put("Contraseña", datos[6]);
                registro.put("Confirmar_Contraseña", datos[2]);*/

                baseDatos.insert(tableName, null, registro);
                baseDatos.close();
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        }

        System.out.println("Datos introducidos: " + datos[0]);
        System.out.println("Datos introducidos: " + datos[1]);
        System.out.println("Datos introducidos: " + datos[2]);
        System.out.println("Datos introducidos: " + datos[3]);
        System.out.println("Datos introducidos: " + datos[4]);
        System.out.println("Datos introducidos: " + datos[5]);
        System.out.println("Datos introducidos: " + datos[6]);



    }


}