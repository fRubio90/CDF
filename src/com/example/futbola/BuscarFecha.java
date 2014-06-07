package com.example.futbola;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BuscarFecha extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_fecha);
		
		final EditText txtDia=(EditText)findViewById(R.id.txtDia);
		final EditText txtMes=(EditText)findViewById(R.id.txtMes);
		final EditText txtAnyo=(EditText)findViewById(R.id.txtAnyo);
		Button btnAceptar=(Button)findViewById(R.id.btnFecha);
		
		
		btnAceptar.setOnClickListener(new OnClickListener(){
			  
			@Override
			public void onClick(View v) {
				
	
				Bundle b=new Bundle();
				b=getIntent().getExtras();
				String [] codigos=b.getStringArray("codigosGrafico");
				
				String dia=txtDia.getText().toString();
				String mes=txtMes.getText().toString();
				String anyo=txtAnyo.getText().toString();
				
				if(dia.matches("[0-9]*") && !dia.equals("") && Integer.parseInt(dia)<32){
					if(mes.matches("[0-9]*") && !mes.equals("") && Integer.parseInt(mes)<13){
						if(anyo.matches("[0-9]*") && !anyo.equals("")){
							
							String fecha=dia+"-"+mes+"-"+anyo;
							
							b.putStringArray("codigosGrafico", codigos);
							b.putString("fecha", fecha);
							
							Intent intent = new Intent(BuscarFecha.this, Graficos.class);
							intent.putExtras(b);
							startActivity(intent);
							finish();
							}
						else{
							TextView lblAnyo=(TextView)findViewById(R.id.lblAnyo);
							lblAnyo.setText("Urtea(uuuu)");
							lblAnyo.setTextColor(Color.RED);
					
							}
					}
					else{
						TextView lblMes=(TextView)findViewById(R.id.lblMes);
						lblMes.setText("Hilabetea(hh)");
						lblMes.setTextColor(Color.RED);
					}
				}
				else{
					TextView lblDia=(TextView)findViewById(R.id.lblDia);
					lblDia.setText("Eguna(ee)");
					lblDia.setTextColor(Color.RED);
				}
			
				
				
			}

		});
	}
}
