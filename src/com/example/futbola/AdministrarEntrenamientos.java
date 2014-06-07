package com.example.futbola;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdministrarEntrenamientos extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administrar_entrenamientos);
		
		final Button btnCrear = (Button)findViewById(R.id.btnCrearEntreno);
		final Button btnCargar = (Button)findViewById(R.id.btnCargarEntreno);
		
		
		  btnCrear.setOnClickListener(new OnClickListener(){
			  
				@Override
				public void onClick(View v) {
					Intent intent= new Intent(AdministrarEntrenamientos.this, AnadirEntrenamiento.class);
	        		
	        		startActivity(intent);
					
				}
	        });
		  
		  btnCargar.setOnClickListener(new OnClickListener(){
			  
				@Override
				public void onClick(View v) {
					Intent intent= new Intent(AdministrarEntrenamientos.this, CargarEntrenamiento.class);
	        		
	        		startActivity(intent);
					
				}
	        });
		
		
	}
	

}
