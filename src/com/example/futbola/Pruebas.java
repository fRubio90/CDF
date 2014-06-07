package com.example.futbola;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Pruebas extends Activity {
	
	private String[] nombres;
	private LinkedList<Integer> codigos=new LinkedList();
	int opcion,codigo;
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_pruebas);
	        
	      ExpandableHeightListView lista =(ExpandableHeightListView)findViewById(R.id.lstPruebas);
	        //ExpandableHeightListView lista =new ExpandableHeightListView(this);
	        lista.setExpanded(true);
	       nombres=lortu();
	       
	       if(nombres!=null){
	    	   ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
	       //ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombres);
	        lista.setAdapter(adapter);
	        
	       }
	
	       Bundle b=new Bundle();
	       b=this.getIntent().getExtras();
	       opcion=Integer.parseInt(b.getString("opcionPrueba"));
	       
	       if(opcion==1 || opcion==2 || opcion==4){ 
	       lista.setOnItemClickListener(
	 	           new AdapterView.OnItemClickListener() {
	 	              public void onItemClick(AdapterView<?> parent,
	 	                 android.view.View v, int position, long id) {
	 	            	  
	 	            	  codigo=codigos.get(position);
	 	            	  deitu(codigo);
	 	            	  //finish();
	 	              }
	 	                 
	 	            	  
	 	           });
	       }
	
	}
	
	
	
	
	public String[] lortu(){
		JugadoresSQLiteHelper jdbh =
                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
     
            SQLiteDatabase db = jdbh.getWritableDatabase();
            Cursor c=null;
                        
            if(db != null){

            	c =db.rawQuery("SELECT * FROM Pruebas",null);
            	String[] nombres= new String[c.getCount()];
        	
            	if(c.moveToFirst()){
            		int i=0;
            		do{
            			String cadena;
//            			nombres[i]=c.getString(0);
//            			nombres[i+1]=c.getString(1);
            			codigos.add(c.getInt(0));
            			cadena=c.getString(2)+"-"+c.getString(1);
            			nombres[i]=cadena;
            			Log.i("parada", "llego"+cadena);
            			//nombres[i+2]=c.getString(2);
            			i++;
            		}while(c.moveToNext());
                	db.close();
                	
                	Log.i("parada", "llego");
                	return nombres;

            	}
            
            }

            return nombres;
	}
	
	public void deitu(int codigo){
		
		if(opcion==1){
			Intent intent= new Intent(this, EditarPrueba.class);
	    	String kodigoa=Integer.toString(codigo);
	    	Bundle b=new Bundle();
	    	b.putString("codigoPrueba", kodigoa);
	    	intent.putExtras(b);
	    	startActivity(intent);
	    	finish();
		}
		
		if(opcion==2){
			
			Intent i = new Intent(this, Mezua2.class);
			startActivityForResult(i, 1);
		}
		
		if(opcion==4){
			
			Intent intent = new Intent(this, BuscarFecha.class);
			//String kodigoa=Integer.toString(codigo);
			Bundle bundle=new Bundle();
			bundle=this.getIntent().getExtras();
			String[] codigos=new String[2];
			codigos[0]=bundle.getString("codGraficoJugador");
			codigos[1]=Integer.toString(codigo);
	    	bundle.putStringArray("codigosGrafico", codigos);
	    	intent.putExtras(bundle);
	    	startActivity(intent);
	    	finish();
		}

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
			            	db.execSQL("DELETE FROM Pruebas WHERE Codigo=?", argumento);
			            	
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