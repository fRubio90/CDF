package com.example.futbola;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class JugadoresSQLiteHelper extends SQLiteOpenHelper {

	//Sentencia SQL para crear la tabla de Usuarios
    
	String tablaJugadores = "CREATE TABLE Jugadores (Codigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Nombre TEXT, Apellido TEXT, Edad INTEGER, Telefono INTEGER, email TEXT, Imagen BLOB)";
	String tablaPruebas = "CREATE TABLE Pruebas (Codigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nombre TEXT, Categoria INTEGER)";
	String tablaEntrenos = "CREATE TABLE Entrenamientos (Codigo INTEGER NOT NULL REFERENCES Jugadores ON DELETE CASCADE	, Nombre TEXT NOT NULL, Resultado INTEGER, Fecha TEXT NOT NULL, Entreno TEXT NOT NULL, PRIMARY KEY(Codigo, Nombre, Entreno))";
	
    public JugadoresSQLiteHelper(Context contexto, String nombre,
                               CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(tablaJugadores);
        db.execSQL(tablaPruebas);
        db.execSQL(tablaEntrenos);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente
        // la opción de eliminar la tabla anterior y crearla de nuevo
        // vacía con el nuevo formato.
        // Sin embargo lo normal será que haya que migrar datos de la
        // tabla antigua a la nueva, por lo que este método debería
        // ser más elaborado.
 
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Jugadores");
        db.execSQL("DROP TABLE IF EXISTS Pruebas");
        db.execSQL("DROP TABLE IF EXISTS Entrenamientos");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(tablaJugadores);
        db.execSQL(tablaPruebas);
        db.execSQL(tablaEntrenos);
    }
    
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
	

