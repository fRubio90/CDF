package com.example.futbola;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdministrarJugadores extends Activity {

	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_administrar_jugadores);
	
	        final Button btnAnadir=(Button)findViewById(R.id.btnAnadirJugadores);
	        final Button btnEditar=(Button)findViewById(R.id.btnEditarJugadores);
	        final Button btnEliminar=(Button)findViewById(R.id.btnEliminarJugadores);
	        final Button btnVer=(Button)findViewById(R.id.btnVerJugadores);
	        
	        
	        
			  btnAnadir.setOnClickListener(new OnClickListener(){
				  
					@Override
					public void onClick(View v) {
						Intent intent= new Intent(AdministrarJugadores.this, AnadirJugadores.class);
		        		startActivity(intent);
						
					}
		        });
	        
			  
			  btnEditar.setOnClickListener(new OnClickListener(){
				  
					@Override
					public void onClick(View v) {
						Intent intent= new Intent(AdministrarJugadores.this, SeleccionarJugadores.class);
						Bundle b=new Bundle();
				        b.putString("opcion", "1");
						intent.putExtras(b);
		        		startActivity(intent);
						
					}
		        });
	        
			  
			  btnEliminar.setOnClickListener(new OnClickListener(){
				  
					@Override
					public void onClick(View v) {
						Intent intent= new Intent(AdministrarJugadores.this, SeleccionarJugadores.class);
						Bundle b=new Bundle();
				        b.putString("opcion", "2");
						intent.putExtras(b);
		        		startActivity(intent);
						
					}
		        });
			  
			  
			  btnVer.setOnClickListener(new OnClickListener(){
				  
					@Override
					public void onClick(View v) {
						Intent intent= new Intent(AdministrarJugadores.this, Jugadores.class);
		        		startActivity(intent);
						
					}
		        });
	        
	        
	        
	}
	
	
	
	
	
	
	
	
	
	
	
}
