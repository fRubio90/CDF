package com.example.futbola;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Perfil extends Activity {
	
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_perfil);
	        
	        
	        Bundle b=new Bundle();
	        b=this.getIntent().getExtras();
	        String[] codigo=new String[1];
	        codigo[0]=b.getString("codigo");
//	        String[] codigo=new String[1];
//	        ape[0]=b.getString("apellido");
	        
	        JugadoresSQLiteHelper jdbh =
	                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
	     
	            SQLiteDatabase db = jdbh.getWritableDatabase();
	            Cursor c=null;
	                        
	            if(db != null){

	            	c =db.rawQuery("SELECT * FROM Jugadores where Codigo=?",codigo);        
	            
	            }
       
	        TextView nombre=(TextView)findViewById(R.id.lblNombrePerfil);
	        TextView apellido=(TextView)findViewById(R.id.lblApellidoPerfil);
	        TextView edad=(TextView)findViewById(R.id.lblEdadPerfil);
	        final TextView telefono=(TextView)findViewById(R.id.lblTelefonoPerfil);
	        final TextView email=(TextView)findViewById(R.id.lblEmailPerfil);
	        ImageView imagen=(ImageView)findViewById(R.id.imgImagen);
	        ImageView iconoTef=(ImageView)findViewById(R.id.iconoTef);
	        iconoTef.setImageResource(android.R.drawable.ic_menu_call);
	        ImageView iconoEmail=(ImageView)findViewById(R.id.iconoEmail);
	        iconoEmail.setImageResource(android.R.drawable.sym_action_email);
	       
	        
	        if(c.moveToFirst()){
	        	
	        	nombre.setText("Izena: "+c.getString(1));
	        	apellido.setText("Abizena: "+c.getString(2));
	        	edad.setText("Adina: "+c.getString(3));
	        	telefono.setText("Telefonoa: "+c.getString(4));
	        	email.setText("Email: "+c.getString(5));
	        	
	        	byte[] blob=c.getBlob(6);
	        	if(blob!=null){
		        	Bitmap bmp=BitmapFactory.decodeByteArray(blob,0,blob.length);
		        	imagen.setImageBitmap(bmp);
	        	}

	        }
	      
	        telefono.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String phone =telefono.getText().toString();
					Log.i("parada","llego a la llamada"+phone);
					Intent callIntent = new Intent(Intent.ACTION_CALL);          
		            callIntent.setData(Uri.parse("tel:"+phone));          
		            startActivity(callIntent); 
					
					
				}
			}); 
	        
	        
	        iconoTef.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String phone =telefono.getText().toString();
					Log.i("parada","llego a la llamada"+phone);
					Intent callIntent = new Intent(Intent.ACTION_CALL);          
		            callIntent.setData(Uri.parse("tel:"+phone));          
		            startActivity(callIntent); 
					
					
				}
			}); 
	        
	        
	        email.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String destinatario[]={email.getText().toString()};
					String mezua="Mezua bidaltzeko tresna:";
					
					Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);    
				    emailIntent.setType("plain/text");    
				    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, destinatario);
				    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, destinatario);

				    startActivity(Intent.createChooser(emailIntent, mezua));
					
					
				}
			});
	        
	        
	        iconoEmail.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String destinatario[]={email.getText().toString()};
					String mezua="Mezua bidaltzeko tresna:";
					
					Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);    
				    emailIntent.setType("plain/text");    
				    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, destinatario);
				    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, destinatario);

				    startActivity(Intent.createChooser(emailIntent, mezua));
					
					
				}
			});
	        
	        
	}
	

}
