package com.rodr.tourcamp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.rodr.tourcamp.Contexto.AppFragments;
import com.rodr.tourcamp.R;
import com.rodr.tourcamp.SQLite.AdminDB;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CompraBoleto extends AppCompatActivity {

    String recibirDatosQR, nombreUser, apellidoP, apellidoM;
    EditText txtNombreCompraBoleto, txtApellidoPCompraBoleto, txtApellidoMCompraBoleto, txtNacionalidadCompraBoleto, txtFechaCompra, txtHoraCompra, txtMetodoPagoCompraBoleto;
    Button btnCompraBoleto;
    TextView txt_RecibirDatosQR;
    ImageView imgDatosCodigoQR;
    Spinner cbxSitioCompraBoleto;
    //DatePicker dtpFechaCompraBoleto;
    //TimePicker tmpHoraCompraBoleto;
    ImageButton btnFechaCompra, btnHoraCompra;

    private int dia, mes, anio, hora, minutos;

    //txtFechaCompraBoleto, txtHoraCompraBoleto

    // DECLARAR UNA INSTANCIA DE FirebaseUser....
    FirebaseUser user;

    // DECLARAR UNA INSTANCIA DE FirebaseFirestore....
    FirebaseFirestore db;

    // DECLARAR UNA INSTANCIA DE FirebaseAuth....
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_boleto);
        initGui();
        inicializarComponentesFirebase();
        siHayUsuarioIniciadoSesion();
        obtenerDatosFirebase(); // OBTENER LOS DATOS O RECUPERARLOS EN LA BD FIREBASE....
        comprarBoleto();
        mostrarFecha();
        mostrarHora();
    }

    private void initGui(){
        txtNombreCompraBoleto = (EditText) findViewById(R.id.txt_Nombre_Compra_Boleto);
        txtApellidoPCompraBoleto = (EditText) findViewById(R.id.txt_ApellidoP_Compra_Boleto);
        txtApellidoMCompraBoleto = (EditText) findViewById(R.id.txt_ApellidoM_Compra_Boleto);
        txtNacionalidadCompraBoleto = (EditText) findViewById(R.id.txt_Nacionalidad_Compra_Boleto);
        cbxSitioCompraBoleto = (Spinner) findViewById(R.id.cbx_Sitio_Compra_Boleto);
        btnFechaCompra = (ImageButton) findViewById(R.id.btn_Fecha_Compra);
        txtFechaCompra = (EditText) findViewById(R.id.txt_Fecha_Compra);
        btnHoraCompra = (ImageButton) findViewById(R.id.btn_Hora_Compra);
        txtHoraCompra = (EditText) findViewById(R.id.txt_Hora_Compra);
        //dtpFechaCompraBoleto = (DatePicker) findViewById(R.id.dtp_Fecha_Compra_Boleto);
        //txtFechaCompraBoleto = (EditText) findViewById(R.id.txt_Fecha_Compra_Boleto);
        //tmpHoraCompraBoleto = (TimePicker) findViewById(R.id.tmp_Hora_Compra_Boleto);
        //txtHoraCompraBoleto = (EditText) findViewById(R.id.txt_Hora_Compra_Boleto);
        txtMetodoPagoCompraBoleto = (EditText) findViewById(R.id.txt_Metodo_Pago_Compra_Boleto);
        btnCompraBoleto = (Button) findViewById(R.id.btn_Compra_Boleto);
        txt_RecibirDatosQR = (TextView) findViewById(R.id.txt_Recibir_Datos_QR);
        imgDatosCodigoQR = (ImageView) findViewById(R.id.img_Datos_Codigo_QR);
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

        // Obtén el usuario con sesión activa....
        if (user != null) {
            // User is signed in
            //txtNombrePerfil.setText(user.getEmail());
        } else {
            // No user is signed in
        }
    }

    // RECUPERAR LOS DATOS EN FIREBASE...
    private void obtenerDatosFirebase(){

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
                txtNombreCompraBoleto.setText(value.getString("Nombre")); // RECUPERO EL NOMBRE, Y SE LAMDO A LA CAJA DE TEXTO, VISUALIZA EL NOMBRE...
                txtApellidoPCompraBoleto.setText(value.getString("Apellido Paterno")); // RECUPERO EL APELLIDO PATERNO, Y SE LAMDO A LA CAJA DE TEXTO, VISUALIZA EL APELLIDO P...
                txtApellidoMCompraBoleto.setText(value.getString("Apellido Materno")); // RECUPERO EL APELLIDO MATERNO, Y SE LAMDO A LA CAJA DE TEXTO, VISUALIZA EL APELLIDO M...
            }
        });
    }

    private void comprarBoleto(){
        btnCompraBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llenarCamposComprarBoleto();
            }
        });
    }

    private void llenarCamposComprarBoleto(){
        /*String dia, mes, anio;
        dia = String.valueOf(dtpFechaCompraBoleto.getDayOfMonth());
        mes = String.valueOf(dtpFechaCompraBoleto.getMonth());
        anio = String.valueOf(dtpFechaCompraBoleto.getYear());
        String formatoFecha = dia + "/" + mes + "/" + anio;

        String hora = "", minuto = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            hora = String.valueOf(tmpHoraCompraBoleto.getHour());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            minuto = String.valueOf(tmpHoraCompraBoleto.getMinute());

        String formatoHora = hora + ":" + minuto;
        */

        if (txtNombreCompraBoleto.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtNombreCompraBoleto.setError("Introduzca su nombre"); // MANDAR UN MENSAJE DE ERROR....
        if (txtApellidoPCompraBoleto.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtApellidoPCompraBoleto.setError("Introduzca su apellido paterno"); // MANDAR UN MENSAJE DE ERROR....
        if (txtApellidoMCompraBoleto.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtApellidoMCompraBoleto.setError("Introduzca su materno"); // MANDAR UN MENSAJE DE ERROR....
        if (txtNacionalidadCompraBoleto.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtNacionalidadCompraBoleto.setError("Introduzca su nacionalidad"); // MANDAR UN MENSAJE DE ERROR....
        if (txtFechaCompra.getText().toString().length() == 0)
            txtFechaCompra.setError("Selecciona la fecha");
        if (txtHoraCompra.getText().toString().length() == 0)
            txtHoraCompra.setError("Seleccione el horario");
        //if (cbxSitioCompraBoleto.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
        //    cbxSitioCompraBoleto.setError("Introduzca el sitio"); // MANDAR UN MENSAJE DE ERROR....
        //if (txtFechaCompraBoleto.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
        //    txtFechaCompraBoleto.setError("Introduzca la fecha"); // MANDAR UN MENSAJE DE ERROR....
        //if (txtHoraCompraBoleto.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
        //    txtHoraCompraBoleto.setError("Introduzca el horario"); // MANDAR UN MENSAJE DE ERROR....
        if (txtMetodoPagoCompraBoleto.getText().toString().length() == 0) // SI EL CAMPO ESTA VACIO....
            txtMetodoPagoCompraBoleto.setError("Introduzca el metodo de pago"); // MANDAR UN MENSAJE DE ERROR....
        else {
            String id = mAuth.getCurrentUser().getUid(); // OBTENER EL ID DEL USUARIO...
            Map<String, Object> mapCampraBoleto = new HashMap<>(); // OBJETO O VARIABLE DE MAPEO...
            //mapUser.put("id", id);
            mapCampraBoleto.put("Nombre", txtNombreCompraBoleto.getText().toString()); // MANDAMOS EL "NOMBRE" A LA COLECCION PARA REGISTRARSE EN FIREBASE... LO QUE CONTENGA LA CAJA DE TEXTO...
            mapCampraBoleto.put("Apellido Paterno", txtApellidoPCompraBoleto.getText().toString());
            mapCampraBoleto.put("Apellido Materno", txtApellidoMCompraBoleto.getText().toString());
            mapCampraBoleto.put("Nacionalidad", txtNacionalidadCompraBoleto.getText().toString());
            mapCampraBoleto.put("Sitio Turistico", cbxSitioCompraBoleto.getSelectedItem().toString());
            mapCampraBoleto.put("Fecha de viaje", txtFechaCompra.getText().toString());
            mapCampraBoleto.put("Hora de viaje", txtHoraCompra.getText().toString());
            //mapCampraBoleto.put("Fecha de viaje", formatoFecha);
            //mapCampraBoleto.put("Hora de viaje", formatoHora);
            mapCampraBoleto.put("Metodo de pago", txtMetodoPagoCompraBoleto.getText().toString());

            // BD LOCAL...
            int version = AppFragments.version; // VERSION DE BD....
            String dbName = AppFragments.databaseNameTourCamp; // NOMBRE DE LA BD..
            String tableName = AppFragments.tbCompraBoleto; // NOMBRE DE TABLA DE LA BD....

            // INSTANCIA DE LA CLASE....
            AdminDB adminDB = new AdminDB(getApplicationContext(), dbName, null, version);

            // PARA ESCRIBIR EN LA BD...
            SQLiteDatabase baseDatos = adminDB.getWritableDatabase();

            if (baseDatos != null) { // SI LA BD NO EXISTE, CREARLA...
                try {
                    // INSERTAR LOS DATOS EN LA BD LOCAL...
                    ContentValues registro = new ContentValues();
                    registro.put(AppFragments.tbNombreCompraBoleto, txtNombreCompraBoleto.getText().toString());
                    registro.put(AppFragments.tbApellidoPCompraBoleto, txtApellidoPCompraBoleto.getText().toString());
                    registro.put(AppFragments.tbApellidoMCompraBoleto, txtApellidoMCompraBoleto.getText().toString());
                    registro.put(AppFragments.tbNacionalidadCompraBoleto, txtNacionalidadCompraBoleto.getText().toString());
                    registro.put(AppFragments.tbSitioCompraBoleto, cbxSitioCompraBoleto.getSelectedItem().toString());
                    registro.put(AppFragments.tbFechaCompraBoleto, txtFechaCompra.getText().toString());
                    registro.put(AppFragments.tbHoraCompraBoleto, txtHoraCompra.getText().toString());
                    //registro.put(AppFragments.tbFechaCompraBoleto, formatoFecha);
                    //registro.put(AppFragments.tbHoraCompraBoleto, formatoHora);
                    registro.put(AppFragments.tbMetodoPagoCompraBoleto, txtMetodoPagoCompraBoleto.getText().toString());
                    baseDatos.insert(tableName, null, registro); // EJECUTAR LA INSERCION...
                    baseDatos.close(); // CERRAMOS LA BD...
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show(); // ERROR SI NO SE INSERTA...
                }
            }

            db.collection("ComprarBoleto").add(mapCampraBoleto).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getApplicationContext(), "Compra realizada", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
                }
            });

            // QR....

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = null;

            recibirDatosQR = txtNombreCompraBoleto.getText().toString() + " \n " +
                    txtApellidoPCompraBoleto.getText().toString() + " \n " +
                    txtApellidoMCompraBoleto.getText().toString() + "\n " +
                    txtNacionalidadCompraBoleto.getText().toString() + "\n " +
                    cbxSitioCompraBoleto.getSelectedItem().toString() + "\n " +
                    txtFechaCompra.getText().toString() + "\n " +
                    txtHoraCompra.getText().toString() + "\n " +
                    //formatoFecha + "\n " +
                    //formatoHora + "\n " +
                    txtMetodoPagoCompraBoleto.getText().toString();

            txt_RecibirDatosQR.setText(recibirDatosQR);

            try {
                bitmap = barcodeEncoder.encodeBitmap(txt_RecibirDatosQR.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);
                imgDatosCodigoQR.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }

    private void obtenerQR(){
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = null;

        recibirDatosQR = txtNombreCompraBoleto.getText().toString() + " \n " +
                txtApellidoPCompraBoleto.getText().toString() + " \n " +
                txtApellidoMCompraBoleto.getText().toString() + "\n " +
                txtNacionalidadCompraBoleto.getText().toString() + "\n " +
                cbxSitioCompraBoleto.getSelectedItem().toString() + "\n " +
                //formatoFecha.getText().toString() + "\n " +
                //txtHoraCompraBoleto.getText().toString() + "\n " +
                txtMetodoPagoCompraBoleto.getText().toString();

        txt_RecibirDatosQR.setText(recibirDatosQR);

        try {
            bitmap = barcodeEncoder.encodeBitmap(txt_RecibirDatosQR.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);
            imgDatosCodigoQR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void mostrarFecha(){
        btnFechaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        txtFechaCompra.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, dia, mes, anio);
                datePicker.show();
            }
        });
    }

    private void mostrarHora(){
        btnHoraCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                hora = calendar.get(Calendar.HOUR_OF_DAY);
                minutos = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        txtHoraCompra.setText(hourOfDay + ":" + minute);
                    }
                }, hora, minutos, true);
                timePicker.show();
            }
        });
    }
}