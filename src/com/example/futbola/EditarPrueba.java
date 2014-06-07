package com.example.futbola;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditarPrueba extends Activity {

	String kodigoa;
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_anadir_pruebas);
	        
	        
	        Bundle b=new Bundle();
	        b=this.getIntent().getExtras();
	        kodigoa=b.getString("codigoPrueba");
	        
	        Button btnGuardar=(Button)findViewById(R.id.btnGuardarPruebas);
	        
	        datuakLortu();
	        
//	        btnGuardar.setOnClickListener(new OnClickListener(){
//				  
//				@Override
//				public void onClick(View v){
//					gorde();
//					mezua();
//					
//				}
//			});
	        btnGuardar.setOnClickListener(new OnClickListener(){
				  
				@Override
				public void onClick(View v) {
					gorde();
					mezua();

					
				}
	        });
	
	
	}
	
public void datuakLortu(){
	
	
    
    EditText txtNombre=(EditText)findViewById(R.id.txtPrueba);
    EditText txtCategoria=(EditText)findViewById(R.id.txtCategoria);
	
	JugadoresSQLiteHelper jdbh =
            new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
 
	String[] izenak=new String[1];
	
        SQLiteDatabase db = jdbh.getReadableDatabase();
        Cursor c=null;
                    
        if(db != null){

        	String[] parametro=new String[1];
        	parametro[0]=kodigoa;
        	c =db.rawQuery("SELECT * FROM Pruebas WHERE codigo=?",parametro);
        	if(c.moveToFirst()){
        		do{
        			txtNombre.setText(c.getString(1));
        			txtCategoria.setText(c.getString(2));
        		}while(c.moveToNext());
            	db.close();
        		}
        }

	}


public void gorde(){
	
	 JugadoresSQLiteHelper jdbh =
               new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
    
           SQLiteDatabase db = jdbh.getWritableDatabase();
           Cursor c=null;
    
           //Si hemos abierto correctamente la base de datos
           if(db != null){
	
//	db.execSQL("DELETE FROM JUGADORES WHERE Nombre=nombre");
	
	//Creamos las variables
	
	EditText txtNombre=(EditText)findViewById(R.id.txtPrueba);
	EditText txtCategoria=(EditText)findViewById(R.id.txtCategoria);


	ContentValues cv = new  ContentValues();
	//NULL autoincrement betetzeko
	//if batzuk jarriko doguz arazoak ekiditzeko
	//Guardar ez zen agertu beharko, derrigorrezko datu batzuk sartu arte EGITEKO!!

	//cv.put("Nombre",txtNombre.getText().toString() );
	cv.put("Nombre",txtNombre.getText().toString());
	cv.put("Categoria", txtCategoria.getText().toString());
	
	String[] argumento=new String[1];
	argumento[0]=kodigoa;
  db.update("Pruebas",cv,"Codigo=?",argumento);
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