package com.example.futbola;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnadirPruebas extends Activity {
	
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_anadir_pruebas);
	        
	        Button btnGuardar=(Button)findViewById(R.id.btnGuardarPruebas);
	        
	        btnGuardar.setOnClickListener(new OnClickListener(){
				  
				@Override
				public void onClick(View v){
					
					EditText txtNombre=(EditText)findViewById(R.id.txtPrueba);
					
					if(txtNombre.getText().toString().equals("")){
						TextView lblDatos=(TextView)findViewById(R.id.lblDatos2);
						lblDatos.setText("Sartu Probaren izena");
						lblDatos.setTextColor(Color.RED);
					}
					else{
					
					gorde();
					mezua();
					}
					
				}
			});
	        
	
	
	}
	
	
	
	public void gorde(){
		
		 JugadoresSQLiteHelper jdbh =
	                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
	     
	            SQLiteDatabase db = jdbh.getWritableDatabase();
	            Cursor c=null;
	     
	            //Si hemos abierto correctamente la base de datos
	            if(db != null){
		
//		db.execSQL("DELETE FROM JUGADORES WHERE Nombre=nombre");
		
		//Creamos las variables
		
		EditText txtNombre=(EditText)findViewById(R.id.txtPrueba);
       EditText txtCategoria=(EditText)findViewById(R.id.txtCategoria);


   	ContentValues cv = new  ContentValues();
   	//NULL autoincrement betetzeko
   	cv.putNull("Codigo");
   	//if batzuk jarriko doguz arazoak ekiditzeko
   	//Guardar ez zen agertu beharko, derrigorrezko datu batzuk sartu arte EGITEKO!!

   	//cv.put("Nombre",txtNombre.getText().toString() );
   	cv.put("Nombre",txtNombre.getText().toString());
   	cv.put("Categoria", txtCategoria.getText().toString());
   	
   	
       db.insert("Pruebas", null, cv );
       db.close();
	
	            }
	            
	}
	
	public void mezua(){
		Intent i = new Intent(this, Mezua.class);
		startActivityForResult(i, 1);
	}
	

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
	 if (resultCode == RESULT_CANCELED) {    
	        finish();
	    }
	}
	
	
	
}
