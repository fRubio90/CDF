package com.example.futbola;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class Jugadores extends Activity {
	
	private String[] nombres;
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_jugadores);
	       
	        GridView gridCabeceras=(GridView)findViewById(R.id.gridCabecerasJugadores);
	        gridCabeceras.setNumColumns(3);
	        String[] cabeceras={"Kodigoa","Izena","Abizena"};
	        AdaptadorCabeceras adaptadorCabeceras=new AdaptadorCabeceras(this, cabeceras);
	        gridCabeceras.setAdapter(adaptadorCabeceras);
	        
	        GridView gridJugadores=(GridView)findViewById(R.id.gridJugadores);
	        gridJugadores.setNumColumns(3);
	        nombres=lortu();
	        
	        //nombres[0]="HOLA";
	        //nombres[1]="kaixo";
	        
	        if(nombres!=null){
	        
	        //ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombres);
	        AdaptadorDatos adapter = new AdaptadorDatos(this,nombres);	
	        gridJugadores.setAdapter(adapter);
	        }
	        	
	        gridJugadores.setOnItemClickListener(
	           new AdapterView.OnItemClickListener() {
	              public void onItemClick(AdapterView<?> parent,
	                 android.view.View v, int position, long id) {
	            	  if(position % 3 == 0)
	                    deitu(nombres[position]);
	            	  else
	            		  if(position % 3 == 1)
	            			  deitu(nombres[position-1]);
	            		  else
	            			  deitu(nombres[position-2]);
	            			  
	                    
	            	  
	                 }
	           });
	        
	        
	}
	
	
	public String[] lortu(){
		JugadoresSQLiteHelper jdbh =
                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
     
            SQLiteDatabase db = jdbh.getWritableDatabase();
            Cursor c=null;
                        
            if(db != null){

            	c =db.rawQuery("SELECT Codigo,Nombre,Apellido FROM Jugadores",null);
            	String[] nombres= new String[c.getCount()*3];
        	
            	if(c.moveToFirst()){
            		int i=0;
            		do{
            			nombres[i]=c.getString(0);
            			nombres[i+1]=c.getString(1);
            			nombres[i+2]=c.getString(2);
            			i=i+3;
            		}while(c.moveToNext());
                	db.close();
                	
	
                	return nombres;
            	}
        	

//        	db.close();
//        	
//       	
//        	return nombres;
            }
            
        	
        	
        	//byte[] blob=c.getBlob(5);
        	
//        	
//        	Bitmap bmp=BitmapFactory.decodeByteArray(blob,0,blob.length);
//        	prueba.setImageBitmap(bmp);
           
            //No sirve para nada. No debería llegar aqui. Es simplemente para hacer el return
           // String[] nombres =new String[3];

        return nombres;
	}
	
public void deitu(String codigo){
	
	//Adaptarlo PARA CODIGO EN VEZ DE APELLIDO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! aFECTA A PERFIL!!
        Intent intent= new Intent(this, Perfil.class);
        Bundle b=new Bundle();
        b.putString("codigo", codigo);
		intent.putExtras(b);
		startActivity(intent);
	}
	

}
