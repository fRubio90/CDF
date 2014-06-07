package com.example.futbola;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdministrarPruebas extends Activity {
	
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_administrar_pruebas);
	
	        final Button btnAnadir=(Button)findViewById(R.id.btnAnadirPruebas);
	        final Button btnEditar=(Button)findViewById(R.id.btnEditarPruebas);
	        final Button btnEliminar=(Button)findViewById(R.id.btnEliminarPruebas);
	        final Button btnVer=(Button)findViewById(R.id.btnVerPruebas);
	        
	        
	        
			  btnAnadir.setOnClickListener(new OnClickListener(){
				  
					@Override
					public void onClick(View v) {
						Intent intent= new Intent(AdministrarPruebas.this, AnadirPruebas.class);
		        		startActivity(intent);
						
					}
		        });
			  
			  
			  btnEditar.setOnClickListener(new OnClickListener(){
				  
					@Override
					public void onClick(View v) {
						Intent intent= new Intent(AdministrarPruebas.this, Pruebas.class);
						Bundle b=new Bundle();
						b.putString("opcionPrueba", "1");
						intent.putExtras(b);
		        		startActivity(intent);
						
					}
		        });
			  
			  
			  btnEliminar.setOnClickListener(new OnClickListener(){
				  
					@Override
					public void onClick(View v) {
						Intent intent= new Intent(AdministrarPruebas.this, Pruebas.class);
						Bundle b=new Bundle();
						b.putString("opcionPrueba", "2");
						intent.putExtras(b);
		        		startActivity(intent);
						
					}
		        });
			  
			  btnVer.setOnClickListener(new OnClickListener(){
				  
					@Override
					public void onClick(View v) {
						Intent intent= new Intent(AdministrarPruebas.this, Pruebas.class);
						Bundle b=new Bundle();
						b.putString("opcionPrueba", "3");
						intent.putExtras(b);
		        		startActivity(intent);
						
					}
		        });
			  
			  
			  
	 }
			  
			  
}


