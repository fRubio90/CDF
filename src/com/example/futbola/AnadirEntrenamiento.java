package com.example.futbola;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class AnadirEntrenamiento extends Activity {
	ExpandableHeightListView lista;
	
	int estado=0;
	LinkedList<String> pruebas =new LinkedList<String>();
	LinkedList<Integer> zenbakiak = new LinkedList<Integer>(); 
	LinkedList<Integer> codigos = new LinkedList<Integer>(); 
	String[] nombres;
	
	AdaptadorCabeceras adaptadorC;
	AdaptadorEditText adaptador;
	
	EditText txtNombreEntreno;
	
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_anadir_entrenamiento);
	        
	        final Button btnJugadores=(Button)findViewById(R.id.btnJugadoresEntreno);
	        final Button btnPruebas=(Button)findViewById(R.id.btnPruebasEntreno);
	        final Button btnEntreno=(Button)findViewById(R.id.btnEntreno);
	        final Button btnGuardar=(Button)findViewById(R.id.btnGuardarEntreno);
	        //final TextView 
	        
	        final TextView lblOrden=(TextView)findViewById(R.id.lblEntreno);

	        final ExpandableHeightGridView gridCabeceras=(ExpandableHeightGridView)findViewById(R.id.gridCabecerasEntreno);
	        final ExpandableHeightGridView gridResultados=(ExpandableHeightGridView)findViewById(R.id.gridEntreno);

	        lista =(ExpandableHeightListView)findViewById(R.id.lstEntreno);
	        final ExpandableHeightListView jokalariak=(ExpandableHeightListView)findViewById(R.id.lstTabla);
	        
	        txtNombreEntreno=(EditText)findViewById(R.id.txtNombreEntreno);
	        
	        gridCabeceras.setExpanded(true);
	        gridResultados.setExpanded(true);
	        lista.setExpanded(true);
	        jokalariak.setExpanded(true);
	        
//	        nombres=lortuProbak();
//	        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombres);
//	        lista.setAdapter(adapter);
	        
	        
	        
	        btnGuardar.setOnClickListener(new OnClickListener(){
				  
				@Override
				public void onClick(View v) {
					gorde();
////					JugadoresSQLiteHelper jdbh = new JugadoresSQLiteHelper (this, "DBJugadores", null, 1);
////					
////					//JugadoresSQLiteHelper jdbh = new ();
////			          //      new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
////			     
////			            SQLiteDatabase db = jdbh.getWritableDatabase();
////			            //Cursor c=null;
////			     
////			            //Si hemos abierto correctamente la base de datos
////			            if(db != null){
////					
////					
////						int contador=0;
////						
////						for(int i=0; i<codigos.size(); i++){
////							ContentValues cv=new ContentValues();
////							int cod=codigos.get(i);
////							//cv.put("Codigo",cod);
////							
////							for(int u=0; u<pruebas.size(); u++){
////								String prueba=pruebas.get(u);
////								String fecha=txtFecha.getText().toString();
////								EditText celda=(EditText)adaptador.getItem(contador);
////								int resultado=Integer.parseInt(celda.getText().toString());
////								Log.i("parada", "valor de ley: "+celda.getText().toString());
////								contador++;
////								Log.i("Parada", "Valor del contador"+contador);
////								
////							}
////							
////							
////						}
////						
////			            }	
					}
		        });
		        
	        
	
	        
	        btnPruebas.setOnClickListener(new OnClickListener(){
				  
				@Override
				public void onClick(View v) {
					
					
					lista.setVisibility(0);
					jokalariak.setVisibility(8);
					gridCabeceras.setVisibility(4);
					gridResultados.setVisibility(8);
					
						if(pruebas.size()!=0){
							pruebas.clear();
						}
					  	estado=0;
					    nombres=lortuProbak();
					    kargatuLista(nombres); 
					    lblOrden.setText("Selecciona las pruebas a medir:");

				}
	        });
	        
	        
	        btnJugadores.setOnClickListener(new OnClickListener(){
				  
				@Override
				public void onClick(View v) {

					lista.setVisibility(0);
					jokalariak.setVisibility(8);
					gridCabeceras.setVisibility(4);
					gridResultados.setVisibility(8);
					
					if(codigos.size()!=0){
						codigos.clear();
					}
						
					  	estado=1;
					    nombres=lortuJokalariak();
					    kargatuLista(nombres); 
					    lblOrden.setText("Aukeratu jokalariak");
				}
	        });
	        
	        
	        
	        btnEntreno.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					for(int i=0; i<adaptador.getCount(); i++){
//						String s=adaptador.update(i);
//						lblOrden.setText("Cambio"+s);
//					}
					
					
//					String s=adaptadorR.update(0);
//					lblOrden.setText("CAMBIO: "+s);
					
					lista.setVisibility(8);
					jokalariak.setVisibility(0);
					gridCabeceras.setVisibility(0);
					gridResultados.setVisibility(0);
					recuperarNombres(jokalariak);
					cabecerasTaulaOsatu(gridCabeceras);
					jokalariTaulaOsatu(gridResultados);

				}
			});
	        
	        

	        
	        
	        
	        
	        lista.setOnItemClickListener(
	 	           new AdapterView.OnItemClickListener() {
	 	              public void onItemClick(AdapterView<?> parent,
	 	                 android.view.View v, int position, long id) {
	 	            	  
	 	            	CheckedTextView check = (CheckedTextView)v;
	 	                check.setChecked(!check.isChecked());
	 	            	  
	 	            	  if(estado==0){
	 	            		  
	 	            		  if(!pruebas.contains(nombres[position])){
	 	            			  pruebas.add(nombres[position]);
		 	            	  

	 	            		  }
	 	            		  else{

	 	            			  pruebas.remove(nombres[position]);
	 	            		  }
	 	            	  }
	 	            	  
	 	            	  
	 	            	 if(estado==1){
	 	            		  
	 	            		  if(!codigos.contains(zenbakiak.get(position))){
	 	            			  codigos.add(zenbakiak.get(position));

	 	            		  }
	 	            		  else{

	 	            			  for(int i=0; i<codigos.size();i++){
	 	            				 Log.i("parada","position "+position);
	 	            				  if(codigos.get(i)==zenbakiak.get(position)){
	 	            					 codigos.remove(i);
	 	            					 return;
	 	            				  }
	 	            			  }
	 	            		  }
	 	            	  }
	 	                 
	 	            	 
	 	              
	 	              }
	 	           });
	 	        

	        
	       
	        
	        
	       
	        
	}
	
	
	public String[] lortuProbak(){
		JugadoresSQLiteHelper jdbh =
                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
     
            SQLiteDatabase db = jdbh.getWritableDatabase();
            Cursor c=null;
                        
            if(db != null){

            	c =db.rawQuery("SELECT Nombre FROM Pruebas",null);
            	String[] nombres= new String[c.getCount()];
        	
            	if(c.moveToFirst()){
            		int i=0;
            		do{
            			nombres[i]=c.getString(0);
            			i++;
            		}while(c.moveToNext());
                	db.close();
                	return nombres;

            	}
            
            }
            return nombres;
	}
	
	
	
	public String[] lortuJokalariak(){
		JugadoresSQLiteHelper jdbh =
                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
     
            SQLiteDatabase db = jdbh.getWritableDatabase();
            Cursor c=null;
                        
            if(db != null){

            	c =db.rawQuery("SELECT Codigo,Nombre,Apellido FROM Jugadores",null);
            	String[] nombres= new String[c.getCount()];
        	
            	if(c.moveToFirst()){
            		int i=0;
            		do{
            			zenbakiak.add(c.getInt(0));
            			nombres[i]=c.getString(1)+" "+c.getString(2);
            			i++;
            		}while(c.moveToNext());
                	db.close();
                	return nombres;

            	}
            
            }
            return nombres;
	}
	
	
	public void kargatuLista(String[]nombres){
		
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,nombres);
        lista.setAdapter(adapter);
        
		
	}
	
	public void cabecerasTaulaOsatu(GridView cabeceras){
		
		nombres=new String[pruebas.size()+1];
		for(int i=0; i<pruebas.size();i++)
			nombres[i+1]=pruebas.get(i);
		nombres[0]=" ";
		

		cabeceras.setNumColumns(pruebas.size()+1);
		adaptadorC=new AdaptadorCabeceras(this,nombres);
		cabeceras.setAdapter(adaptadorC);
		

	}
	
	public void jokalariTaulaOsatu(GridView jugadores){

//		nombres=new String[codigos.size()+1];
//		for(int i=0; i<codigos.size(); i++){
//			nombres[i]=codigos.get(i).toString();
//		}
//		nombres=new String[4];
//		nombres[1]="kuku";
//		nombres[2]="kuku";
//		nombres[3]="kuku";
//		nombres[0]="kuku";
//		jugadores.setNumColumns(2);
//		adaptadorR=new AdaptadorResultados(this, nombres);
//		jugadores.setAdapter(adaptadorR);

		int num=pruebas.size()*codigos.size();
		nombres=new String[num];
		for(int i=0; i<nombres.length;i++)
			nombres[i]="";
		jugadores.setNumColumns(pruebas.size());
		adaptador=new AdaptadorEditText(this,nombres,num);
		jugadores.setAdapter(adaptador);

		

		
	}
	
	
	public void recuperarNombres(ListView jokalariak){
		
		//int u=0;
		String[] nombres= new String[codigos.size()];;
		
		JugadoresSQLiteHelper jdbh =
                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
     
            SQLiteDatabase db = jdbh.getWritableDatabase();
            Cursor c=null;
                        
            if(db != null){
            	
            	String[] cod=new String[1];
            	for(int i=0; i<codigos.size();i++)
        		{
            		cod[0]=codigos.get(i).toString();
            	    c =db.rawQuery("SELECT Nombre FROM Jugadores WHERE Codigo=?",cod);
                     	
        	
            	    if(c.moveToFirst()){
            	    		nombres[i]=c.getString(0);
	
            	    }
            	    
        		}
            }
            db.close();
		
        //ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
            AdaptadorEditText adapter=new AdaptadorEditText(this,nombres,nombres.length);
        jokalariak.setAdapter(adapter);
                
	
	}
	
public void gorde(){
	JugadoresSQLiteHelper jdbh = new JugadoresSQLiteHelper (this, "DBJugadores", null, 1);
    SQLiteDatabase db = jdbh.getWritableDatabase();

 
        //Si hemos abierto correctamente la base de datos
        if(db != null){
	
	
        	int contador=0;
		
			for(int i=0; i<codigos.size(); i++){
				ContentValues cv=new ContentValues();
				int cod=codigos.get(i);
				cv.put("Codigo",cod);
				
				for(int u=0; u<pruebas.size(); u++){
					String prueba=pruebas.get(u);
					String nombreEntreno=txtNombreEntreno.getText().toString();
					EditText celda=(EditText)adaptador.getItem(contador);
					int resultado;
					if(celda.getText().toString().equals(""))
						resultado=0;
					else
						resultado=Integer.parseInt(celda.getText().toString());

					db.insert("Entrenamientos", null, cv);
					Log.i("parada", "valor de ley: "+celda.getText().toString());
					cv.put("Nombre",prueba);
					cv.put("Resultado", resultado);
					Calendar c = Calendar.getInstance();
					SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
					String fecha=df2.format(c.getTime());
					cv.put("Fecha",fecha);
					cv.put("Entreno", nombreEntreno);
					db.insert("Entrenamientos",null, cv);
					contador++;
					Log.i("Parada", "Valor del contador"+contador);
					
				}
		
			}
		
        }
        db.close();
        mezua();

	}
	
public void mezua(){
	Intent i = new Intent(this, Mezua.class);
	startActivityForResult(i, 1);
}


@Override
protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
 if (resultCode == RESULT_CANCELED) {    
        finish();
    	}

	}

}
