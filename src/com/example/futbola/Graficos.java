package com.example.futbola;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class Graficos extends Activity {

	 private XYPlot mySimpleXYPlot;
	 private LinkedList<String> datak=new LinkedList<String>();
	 private LinkedList<Number> resultados=new LinkedList<Number>();
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_graficos);
	        
	        
	        datuakLortu();
	        Log.i("datuae","datuen tamaina: "+resultados.size());
	        Number[] emaitzak= new Number[resultados.size()];
	        for(int i=0; i<resultados.size();i++)
	        	emaitzak[i]=resultados.get(i);
	        Number[] series2Numbers=emaitzak;
	 
	        // Inicializamos el objeto XYPlot búscandolo desde el layout:
	        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
	 
	        // Creamos dos arrays de prueba. En el caso real debemos reemplazar

	        // estos datos por los que realmente queremos mostrar

	        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
	        //Number[] series2Numbers = {1, 10, 2, 20, 3, 30};
	 
	        // Añadimos Línea Número UNO:
	        XYSeries series1 = new SimpleXYSeries(
	                Arrays.asList(series1Numbers),  // Array de datos
	                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Sólo valores verticales
	                "Series1"); // Nombre de la primera serie
	 
	        // Repetimos para la segunda serie
	        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers
	), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");
	 
	        // Modificamos los colores de la primera serie
	        LineAndPointFormatter series1Format = new LineAndPointFormatter(
	                Color.rgb(0, 200, 0),                   // Color de la línea
	                Color.rgb(0, 100, 0),                   // Color del punto
	                Color.rgb(150, 190, 150), null);              // Relleno
	 
	        // Una vez definida la serie (datos y estilo), la añadimos al panel
	        //mySimpleXYPlot.addSeries(series1, series1Format);
	 
	         //Repetimos para la segunda serie
	        mySimpleXYPlot.addSeries(series2, new LineAndPointFormatter
	(Color.rgb(0, 0, 200), Color.rgb(0, 0, 100), Color.rgb(150, 150, 190), null));
	        
	        
	        String[] fechas=new String[datak.size()];
	        for(int i=0; i<datak.size(); i++)
	        	fechas[i]=datak.get(i);
	        
	        
	 
	        mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("#"));
	        mySimpleXYPlot.getGraphWidget().setDomainValueFormat(new MyIndexFormat(fechas));
	     // draw a domain line for every element plotted on the domain:
	        mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);

	        // get rid of the decimal place on the display:
	        //mySimpleXYPlot.setDomainValueFormat(new DecimalFormat("#"));
	        
	        
	    }
	    
	    
public void datuakLortu(){
	
	Bundle b=new Bundle();
	b=this.getIntent().getExtras();
	
	String fecha=b.getString("fecha");

	SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
    Date data=null;
    try{
    	data=df2.parse(fecha);
    }
    catch (java.text.ParseException e){
    	e.printStackTrace();
    }
    Log.i("heldu", "heldu nazzzzz");	
	
	String[]recibo=b.getStringArray("codigosGrafico");
	String[]codPrueba=new String[1];
	codPrueba[0]=recibo[1];
	//codigos[0]=b.getString("codGraficoJugador");
	Log.i("hj", "hau da jokalariaren kodigoa:"+recibo[0]);
	//codigos[1]=b.getString("codGraficoJugador");
	
	JugadoresSQLiteHelper jdbh =
            new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
 
        SQLiteDatabase db = jdbh.getReadableDatabase();
        Cursor c=null;
                    
        if(db != null){

        	
        	c =db.rawQuery("SELECT Nombre FROM Pruebas WHERE Codigo=? ",codPrueba);
        	if(c.moveToFirst()){
        		do{
        			recibo[1]=c.getString(0);
        		
        		}while(c.moveToNext());
        	}
       	
        	Log.i("heldu", "heldu nazzzzz");	
        	
        	c =db.rawQuery("SELECT Resultado,Fecha FROM Entrenamientos WHERE Codigo=? AND Nombre=?",recibo);
        	//Number[] emaitzak=new Number[c.getCount()];
           
        	if(c.moveToFirst()){
           		//int i=0;
        		do{
        			Date data1=null;
        			try{
        	        	data1=df2.parse(c.getString(1));
        	        }
        	        catch (java.text.ParseException e){
        	        	e.printStackTrace();
        	        }
        		//emaitzak[i]=c.getInt(0);
        		Log.i("data", "hau da data zaharra:"+data);
        		Log.i("data", "hau da data emaitzena:"+data1);
        		
        	       			
        			
        		if(data.before(data1)){
        			resultados.add(c.getInt(0));
        			datak.add(c.getString(1));
        		}
        		//datak.add(c.getString(1));
        		//i++;        			
        		}while(c.moveToNext());
            	db.close();

           	}
        }

	}
	
	
}
