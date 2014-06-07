package com.example.futbola;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

public class CargarEntrenamiento extends Activity {
	
	private EditText txtNombreEntreno;
	private ExpandableHeightGridView gridCabeceras;
	private ExpandableHeightGridView gridResultados;
	private ExpandableHeightListView listaJugadores;
	private int numColumnas;
	

	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_recuperar_entrenamiento);
	        
	        
	        
	        txtNombreEntreno=(EditText)findViewById(R.id.txtCargarEntreno);
	        gridCabeceras=(ExpandableHeightGridView)findViewById(R.id.gridCargarCabecerasEntreno);
	    	gridResultados=(ExpandableHeightGridView)findViewById(R.id.gridCargarEntreno);
	    	listaJugadores=(ExpandableHeightListView)findViewById(R.id.lstCargarTabla);
	        final Button btnCargar=(Button)findViewById(R.id.btnCargarEntreno);
	       
	        gridCabeceras.setExpanded(true);
	        gridResultados.setExpanded(true);
	        listaJugadores.setExpanded(true);
	        
	        
	        btnCargar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					lortu();
					

				
				}
	
	        });
	}
	

	
	public void lortu(){
		JugadoresSQLiteHelper jdbh =
                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
     
            SQLiteDatabase db = jdbh.getWritableDatabase();
            Cursor c=null;
                        
            if(db != null){
            	
            	String[]nombreEntreno=new String[1];
            	nombreEntreno[0]=txtNombreEntreno.getText().toString();
            	c =db.rawQuery("SELECT  DISTINCT Nombre FROM entrenamientos WHERE Entreno=?",nombreEntreno);
            	
            	numColumnas=c.getCount();
            	String[] nombresPruebas= new String[c.getCount()+1];
            	nombresPruebas[0]=" ";
        	
            	if(c.moveToFirst()){
            		int i=1;
            		do{
            			nombresPruebas[i]=c.getString(0);
            			i++;
            		}while(c.moveToNext());
                	
            	}
	
            	cargarCabeceras(nombresPruebas);
            	
            	c =db.rawQuery("SELECT  DISTINCT Codigo FROM entrenamientos WHERE Entreno=?",nombreEntreno);
            	String[] nombresJugadores= new String[c.getCount()];
            	String[] codigosJugadores= new String[c.getCount()];
            	if(c.moveToFirst()){
            		int i=0;
            		do{
            			codigosJugadores[i]=c.getString(0);
            			i++;
            		}while(c.moveToNext());
                	

            	}
            	
            	for(int i=0; i<codigosJugadores.length; i++){
            		String[] codJugador=new String[1];
            		codJugador[0]=codigosJugadores[i];
            		c =db.rawQuery("SELECT Nombre FROM jugadores WHERE Codigo=?",codJugador);
            		if(c.moveToFirst()){
            			nombresJugadores[i]=c.getString(0);
            		}
            		
            		
            	}
            	
            	
            	cargarNombresJugadores(nombresJugadores);
            	
            	c =db.rawQuery("SELECT resultado FROM entrenamientos WHERE Entreno=?",nombreEntreno);
            	String[] resultados= new String[c.getCount()];
            	
            	if(c.moveToFirst()){
            		int i=0;
            		do{
            			resultados[i]=c.getString(0);
            			i++;
            		}while(c.moveToNext());
                	db.close();

            	}
            	
            	cargarResultados(resultados);
            }
	}
	
	
	public void cargarCabeceras(String[] nombres){
		gridCabeceras.setNumColumns(nombres.length);
		AdaptadorCabeceras adaptadorC=new AdaptadorCabeceras(this,nombres);
		gridCabeceras.setAdapter(adaptadorC);
	}
	
	public void cargarNombresJugadores(String[] nombres){
        //ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
		AdaptadorEditText adapter=new AdaptadorEditText(this,nombres,nombres.length);
        listaJugadores.setAdapter(adapter);
	}
	
	
	public void cargarResultados(String[] nombres){
		
		int num=nombres.length;

		gridResultados.setNumColumns(numColumnas);

		AdaptadorEditText adaptador=new AdaptadorEditText(this,nombres,num);
		gridResultados.setAdapter(adaptador);
	}
}
