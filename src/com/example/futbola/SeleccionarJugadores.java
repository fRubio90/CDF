package com.example.futbola;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SeleccionarJugadores extends Activity {
	
	private LinkedList<Integer> codigos;
	private int codigo;
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_seleccionar_jugador);
	        
	        final ListView listaJugadores=(ListView)findViewById(R.id.lstSeleccionarJugadores);
	        
	        //Preparando la lista
	        codigos=new LinkedList<Integer>();
	        String[]izenak=izenakLortu();
	        //String[]izenak={"100","2","3"};
	        if(izenak!=null){
	        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,izenak);
	        listaJugadores.setAdapter(adapter);
	        }
	        
	        //listaJugadores.setVisibility(0);
	        
	        //Log.i("prueba","sartu naizzz"+izenak.length+codigos.size());
	        
	        Log.i("prueba", "PARADA!!!");
	        listaJugadores.setOnItemClickListener(
	 	           new AdapterView.OnItemClickListener() {
	 	              public void onItemClick(AdapterView<?> parent,
	 	                 android.view.View v, int position, long id) {
	 	            	  
	 	            	  codigo=codigos.get(position);
	 	            	  deitu(codigo);
	 	            	  //finish();
	 	              }
	 	                 
	 	            	  
	 	           });
	        
	        
       }
	
	public String[] izenakLortu(){
		JugadoresSQLiteHelper jdbh =
                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
     
		String[] izenak=new String[1];
		
            SQLiteDatabase db = jdbh.getWritableDatabase();
            Cursor c=null;
                        
            if(db != null){

            	c =db.rawQuery("SELECT Codigo,Nombre, Apellido FROM Jugadores",null);
            	String[] nombres= new String[c.getCount()];
        	
            	if(c.moveToFirst()){
            		int i=0;
            		do{
            			codigos.add(c.getInt(0));
            			nombres[i]=c.getString(1)+" "+c.getString(2);
            			i++;
            		}while(c.moveToNext());
                	db.close();
                	return nombres;

            	}
            }
            izenak=null;
            return izenak;
		
	}

	public void deitu(int codigo)
	{
		Bundle b=new Bundle();
	    b=this.getIntent().getExtras();
	    int opcion=Integer.parseInt(b.getString("opcion"));
	    
	    if(opcion==1){
	    	Intent intent= new Intent(this, EditarJugadores.class);
	    	String kodigoa=Integer.toString(codigo);
	    	b.putString("codigo", kodigoa);
	    	intent.putExtras(b);
	    	startActivity(intent);
	    	finish();
	    }

		if(opcion==2){
			
			Intent i = new Intent(this, Mezua2.class);
			startActivityForResult(i, 1);
		}
	    
	    if(opcion==3){
	    	
	    	Intent intent= new Intent(this, Pruebas.class);
	    	String kodigoa=Integer.toString(codigo);
	    	b.putString("codGraficoJugador", kodigoa);
	    	b.putString("opcionPrueba", "4");
	    	intent.putExtras(b);
	    	startActivity(intent);
	    	finish();
	    }
	    
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("p", "sartu naizbat"+codigo);
		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
					JugadoresSQLiteHelper jdbh =
			                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
			     
			            SQLiteDatabase db = jdbh.getWritableDatabase();

			            //Si hemos abierto correctamente la base de datos
			            if(db != null){
			            	String kodigoa=Integer.toString(codigo);
			            	String[] argumento = new String[1];
			            	argumento[0]=kodigoa;
			            	Log.i("p", "sartu naiz"+codigo);
			            	db.execSQL("DELETE FROM Jugadores WHERE Codigo=?", argumento);
			            	
			            } 
			           finish();
		     }
		     if (resultCode == RESULT_CANCELED) {    
		    	 Log.i("p", "sartu naizbat"+codigo);
		         finish();
		     }
		  }
		}//onActiv
	
	
}