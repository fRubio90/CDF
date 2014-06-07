package com.example.futbola;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

public class Dibujo {
	static float cont =0;
	float x;
	float y;
	Bitmap dibujo;
	boolean pasa=false;
	public static boolean pasa2=true;

	
public Dibujo(Bitmap b){
	Log.i("statikoa","balioa: "+cont);
	x=0+cont;
	y=0;
	dibujo=b;
	cont=cont+100;
}
	

public void handleOnTouchEvent(MotionEvent me) {
	 	 
	 if(me.getX()<x)
		 x =(x-(x-me.getX()));
	 else
		 x =(x+(me.getX()-x));
	 
	 if(me.getY()<y)
		 y =(y-(y-me.getY()));
	 else
		 y =(y+(me.getY()-y));
//Kau gero ken daiteke
	 cont=0;
}

public float getX(){
	return x;
}

public float getY(){
	return y;
}

public Bitmap getDibujo(){
	return dibujo;
}


public float getGora(){
	return y-dibujo.getHeight();
}
	
public float getBehera(){
		return y+dibujo.getHeight();
}

public float getEzkerretara(){
	return x-dibujo.getWidth();
}

public float getEskoire(){
	return x+dibujo.getWidth();
}



public float getYBerria(float num){
	float z=0;
	
	if(num<y)
		 z =(y-(y-num));
	 else
		 z =(y+(num-y));
	
	return z;
}

public float getXBerria(float num){
	float z=0;
	
	if(num<x)
		 z =(x-(x-num));
	 else
		 z =(x+(num-x));
	
	return z;
}

public boolean comprobar(ArrayList<Dibujo> l, float numx, float numy){
	
	float cx=this.getXBerria(numx);
	float cy=this.getYBerria(numy);
	
	
	Log.i("parada", "gurea: "+y+" bestea: "+numy);
	
	if(cy<y)
		for(int i=0; i<l.size();i++){
			if(l.get(i)!=this && l.get(i).getBehera()<this.getGora()){
				if(cy>l.get(i).getBehera()+1)
					pasa= true;
				else
					pasa=false;
//				if(cx<l.get(i).getEzkerretara()||cx>l.get(i).getEskoire())
//					return true;
			}
		}
	
	
	if(cy>y)
		for(int i=0; i<l.size();i++){
			if(l.get(i)!=this && l.get(i).getGora()>this.getBehera())
				if((y+this.getDibujo().getHeight())>(l.get(i).getY()-1)){
					pasa=true;
					Log.i("parada","gurea:"+cy+this.getDibujo().getHeight()+"listakoa:"+(l.get(i).getY()-1));
				}
					
				
				else
					pasa=false;
			
		}
	
	return pasa;
	
}

public void setPasa(){
	pasa=true;
}

public boolean getPasa(){
	return pasa;
}

public void gelditu(){
	pasa=false;
}



}