package com.example.futbola;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Botón que nos lleva la menú de jugadores
		//final Button btnAnadirJugadores = (Button)findViewById(R.id.btnAnadirJugadores);
		final Button btnJugadores = (Button)findViewById(R.id.btnJugadores);
		//final Button btnAnadirPruebas = (Button)findViewById(R.id.btnAnadirPruebas);
		final Button btnPruebas = (Button)findViewById(R.id.btnPruebas);
		final Button btnAnadirEntreno = (Button)findViewById(R.id.btnAdministrarEntrenos);
		//final Button btnCargar = (Button)findViewById(R.id.btnCargar);
		final Button btnPizarra = (Button)findViewById(R.id.btnPizarra);
		final Button btnGraficos = (Button)findViewById(R.id.btnGraficos);
		
		//Accion al hacer click en el botón. Nos lleva a la otra ventana
//		  btnAnadirJugadores.setOnClickListener(new OnClickListener(){
//			  
//				@Override
//				public void onClick(View v) {
//					Intent intent= new Intent(MainActivity.this, AnadirJugadores.class);
//	        		
//	        		startActivity(intent);
//					
//				}
//	        });
		  btnJugadores.setOnClickListener(new OnClickListener(){
			  
				@Override
				public void onClick(View v) {
					Intent intent= new Intent(MainActivity.this, AdministrarJugadores.class);
	        		
	        		startActivity(intent);
					
				}
	        });
		  
		  
//		  btnAnadirPruebas.setOnClickListener(new OnClickListener(){
//			  
//				@Override
//				public void onClick(View v) {
//					Intent intent= new Intent(MainActivity.this, AdministrarPruebas.class);
//	        		
//	        		startActivity(intent);
//					
//				}
//	        });
		  
		  
		  btnPruebas.setOnClickListener(new OnClickListener(){
			  
				@Override
				public void onClick(View v) {
					Intent intent= new Intent(MainActivity.this, AdministrarPruebas.class);
	        		
	        		startActivity(intent);
					
				}
	        });
		  
		  btnAnadirEntreno.setOnClickListener(new OnClickListener(){
			  
				@Override
				public void onClick(View v) {
					Intent intent= new Intent(MainActivity.this, AdministrarEntrenamientos.class);
	        		
	        		startActivity(intent);
					
				}
	        });
		  
//		  btnCargar.setOnClickListener(new OnClickListener(){
//			  
//				@Override
//				public void onClick(View v) {
//					Intent intent= new Intent(MainActivity.this, CargarEntrenamiento.class);
//	        		
//	        		startActivity(intent);
//					
//				}
//	        });
		
		  btnPizarra.setOnClickListener(new OnClickListener(){
			  
				@Override
				public void onClick(View v) {
					Intent intent= new Intent(MainActivity.this, Pizarra.class);
	        		
	        		startActivity(intent);
					
				}
	        });
		  
		  
		  btnGraficos.setOnClickListener(new OnClickListener(){
			  
				@Override
				public void onClick(View v) {
					Bundle b=new Bundle();
					b.putString("opcion", "3");
					Intent intent= new Intent(MainActivity.this, SeleccionarJugadores.class);
	        		intent.putExtras(b);
	        		startActivity(intent);
					
				}
	        });
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
