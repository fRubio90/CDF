package com.example.futbola;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Mezua2 extends Activity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje2);
        
        Button btnAceptar=(Button)findViewById(R.id.btnAceptarMensaje2);
        Button btnCancelar=(Button)findViewById(R.id.btnCancelarMensaje2);
        
        btnAceptar.setOnClickListener(new OnClickListener(){
			  
			@Override
			public void onClick(View v) {
				
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);        
				finish();
				
			}
        });
        
        btnCancelar.setOnClickListener(new OnClickListener(){
			  
			@Override
			public void onClick(View v) {
				
				Intent returnIntent = new Intent();
				setResult(RESULT_CANCELED, returnIntent);        
				finish();
				
			}
        });
        
	}

}
