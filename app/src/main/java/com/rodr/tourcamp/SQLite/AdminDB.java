package com.rodr.tourcamp.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.rodr.tourcamp.Contexto.AppFragments;

public class AdminDB extends SQLiteOpenHelper {

    // TABLA DE USUARIOS EN LA BD....
    String dbName = AppFragments.databaseNameTourCamp; // CREO NUEVA UNA VARIABLE DE MI NOMBRE DE BD...
    String tableNameUser = AppFragments.tbUsuarios; // CREO UNA NUEVA VARIABLE DE MI NOMBRE DE TABLA DE BD...

    // CREAO NUEVAS VARIABLES PARA LOS CAMPOS DE MI TABLA DE MI BD...
    String correo_user = AppFragments.tbCorreoUser;
    String nombre_user = AppFragments.tbNombreUser;
    String apellido_p = AppFragments.tbApellidoPaternoUser;
    String apellido_m = AppFragments.tbApellidoMaternoUser;
    String genero = AppFragments.tbGeneroUser;
    String password = AppFragments.tbContraseniaUser;
    String confir_pass = AppFragments.tbConfirmarContraseñaUser;

    // CREAMOS LA TABLA DE LA BASE DE DATOS...
    String crearTablaUsuarios = "CREATE TABLE " + tableNameUser + "(" +
            correo_user + " TEXT NOT NULL," +
            nombre_user + " TEXT NOT NULL," +
            apellido_p + " TEXT NOT NULL," +
            apellido_m + " TEXT NOT NULL," +
            genero + " TEXT NOT NULL," +
            password + " TEXT NOT NULL," +
            confir_pass + " TEXT NOT NULL);";

    /*String crearTablaUsuarios = "CREATE TABLE Usuarios(" +
            "Correo TEXT NOT NULL);"; */


    // TABLA DE CompraBoleto EN LA BD....
    String tableNameBuyTicket = AppFragments.tbCompraBoleto; // CREO UNA NUEVA VARIABLE DE MI NOMBRE DE TABLA DE BD...

    // CREAO NUEVAS VARIABLES PARA LOS CAMPOS DE MI TABLA DE MI BD...
    String nombre_compra = AppFragments.tbNombreCompraBoleto;
    String apellido_paterno_compra = AppFragments.tbApellidoPCompraBoleto;
    String apellido_materno_compra = AppFragments.tbApellidoMCompraBoleto;
    String nacionalidad_compra = AppFragments.tbNacionalidadCompraBoleto;
    String sitio_compra = AppFragments.tbSitioCompraBoleto;
    String fecha_compra = AppFragments.tbFechaCompraBoleto;
    String hora_compra = AppFragments.tbHoraCompraBoleto;
    String metodo_pago_compra = AppFragments.tbMetodoPagoCompraBoleto;

    // CREAMOS LA TABLA DE LA BASE DE DATOS...
    String crearTablaCompraBoleto = "CREATE TABLE " + tableNameBuyTicket + "(" +
            nombre_compra + " TEXT NOT NULL," +
            apellido_paterno_compra + " TEXT NOT NULL," +
            apellido_materno_compra + " TEXT NOT NULL," +
            nacionalidad_compra + " TEXT NOT NULL," +
            sitio_compra + " TEXT NOT NULL," +
            fecha_compra + " TEXT NOT NULL," +
            hora_compra + " TEXT NOT NULL," +
            metodo_pago_compra + " TEXT NOT NULL);";

    public AdminDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL(crearTablaUsuarios); // EXECUTAMOS LA INSTRUCCION DE CREAR TABLA....
        BaseDeDatos.execSQL(crearTablaCompraBoleto); // EXECUTAMOS LA INSTRUCCION DE CREAR TABLA....
    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int i, int i1) {
        //String dropTable = "DROP TABLE Usuarios";
        //BaseDeDatos.execSQL(dropTable);
        //onCreate(BaseDeDatos);
    }
}
