package com.example.futbola;

import java.util.Date;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;

public class MyIndexFormat extends Format {

	private static String[] LABELS; //={"Label 1", "Label 2", "Label 3", "Label 4", "Label 5","Label 6"};

	
	MyIndexFormat(String[] datak){
		super();
		LABELS=datak;
	}

    @Override
    public StringBuffer format(Object object, StringBuffer buffer, FieldPosition field) {
        int parsedInt = Math.round(Float.parseFloat(object.toString()));
        
        

        
        Calendar c = Calendar.getInstance();
        Log.i("bat","data:"+c.getTime());
        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
        String prueba=("11-02-1990");
        Date fecha=null;
        try{
        	fecha=df2.parse(prueba);
        }
        catch (java.text.ParseException e){
        	e.printStackTrace();
        }

        Log.i("bi","dataBi:"+fecha);
        String data = df2.format(c.getTime());
        if(fecha.before(c.getTime()));
        	Log.i("data","handiagoa da");
        
//        LABELS[0]=data;
//        LABELS[1]=data;
//        LABELS[2]=data;
        

        	
        
        String labelString = LABELS[parsedInt];

        buffer.append(labelString);
        return buffer;
    }

    @Override
    public Object parseObject(String string, ParsePosition position) {
        return java.util.Arrays.asList(LABELS).indexOf(string);
    }
}