package com.example.futbola;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Pizarra extends Activity {
	
	PizarraVirtual pizarra;
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	         pizarra= new PizarraVirtual(this);
	        setContentView(pizarra);
	        
	        
	        
	}

	
	class PizarraVirtual extends View{
		
		float x=50;
		float y=50;
		
		String accion="accion";
		
		Path path= new Path();
		
		Bitmap fondo;
		
		ArrayList<Dibujo> lista =new ArrayList<Dibujo>();
		
		int opcion=0;
		
		
		public PizarraVirtual(Context context) {
			super(context);
			//fondo=BitmapFactory.decodeResource(getResources(), R.drawable.campo);
			
//			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//			Display display = wm.getDefaultDisplay();
//			//Display display = getWindowManager().getDefaultDisplay();
//			Point size = new Point();
//			int width = display.getWidth();  
//			int height = display.getHeight();
			
			
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);   
	        int height = metrics.heightPixels-50;
	        int width = metrics.widthPixels;


			fondo=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.campo),width,height,true);
			
			Bitmap dibujo=BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_delete);
			Bitmap dibujo2=BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_dialog_alert);
			lista.add(new Dibujo(dibujo));
			lista.add(new Dibujo(dibujo2));

		}

		protected void onDraw(Canvas canvas) {
			
			//Bitmap scaled=Bitmap.createScaledBitmap(fondo, canvas.getWidth(), canvas.getHeight(), true);
			
			//Bitmap fondo=BitmapFactory.decodeResource(getResources(), R.drawable.campo2);
			canvas.drawBitmap(fondo,0,0, null);
			//scaled.recycle();
			
//			Bitmap dibujo=BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_delete);
//			Bitmap dibujo2=BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_delete);
			//canvas.drawBitmap(dibujo,0,0,null);
			Paint pincel=new Paint();
			
			pincel.setStyle(Paint.Style.STROKE);
			pincel.setStrokeWidth(6);
			pincel.setColor(Color.BLUE);
			
			if(opcion==1){
				
				if(accion.equals("down")){
				
				path.moveTo(x, y);
				}
			
				if(accion.equals("move")){
				
				path.lineTo(x, y);
				
				}
				
			}
			

			
				
			//if(opcion==1)
			canvas.drawPath(path, pincel);
			//canvas.drawBitmap(dibujo, x, y, null);
			
//			canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getWidth()/8, pincel);
//			canvas.drawLine(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()/2, pincel);
			
			
			  for (Dibujo cp : lista){
	                canvas.drawBitmap(cp.getDibujo(),cp.getX(),cp.getY(),null);
	                Log.i("parada", "posizioa: "+cp.getX() );
	                
			  }
			
		}
		
		
		public boolean onTouchEvent(MotionEvent e){
			
			x=e.getX();
			y=e.getY();
			
			
			if(e.getAction()==MotionEvent.ACTION_DOWN){
				accion="down";
				if(opcion==0 || opcion==2){
				for(int i=0; i<lista.size(); i++){
					if(e.getY()<lista.get(i).getBehera() && e.getY()>lista.get(i).getY() && e.getX()<lista.get(i).getEskoire() && e.getX()>lista.get(i).getX())
						{
						
						if(opcion==0)
							if(lista.get(i).pasa2){
								lista.get(i).setPasa();
								lista.get(i).pasa2=false;
							}
						
							if(opcion==2){
									lista.remove(i);
									opcion=0;
							}
						//Log.i("parada", "ikutua: "+i);
							
						
						}
				}
				}
				
				
			}
			
			if(e.getAction()==MotionEvent.ACTION_MOVE){
				accion="move";
			if(opcion==0){	
				for(int i=0; i<lista.size(); i++){
					if(lista.get(i).getPasa()){
						lista.get(i).handleOnTouchEvent(e);
						Log.i("parada", "ikutua: "+i+"mugitzen");
					}
				}
			}
			}
			
			if(e.getAction()==MotionEvent.ACTION_UP){
				if(opcion==0){
				for(int i=0; i<lista.size(); i++){
					lista.get(i).gelditu();
					lista.get(i).pasa2=true;
				}
			}
				
				
			}	
			 //for (Dibujo cp : lista){
			for(int i=0; i<lista.size(); i++){
//				if(e.getY()<lista.get(i).getBehera() && e.getY()>lista.get(i).getGora() && e.getX()<lista.get(i).getEskoire() && e.getX()>lista.get(i).getEzkerretara())
//					if(lista.get(i).comprobar(lista, e.getX(), e.getY()))
//						lista.get(i).handleOnTouchEvent(e);
//					
					
					//					for(int u=0; u<lista.size();u++)
//						if(lista.get(u)!=lista.get(i)){
//							if(lista.get(i).getYBerria(e.getY())>(lista.get(u).getBehera()+1))
//								lista.get(i).handleOnTouchEvent(e);
//						}
			 }      
			

			invalidate();
			return true;
		}
		
		
//		public boolean onTouchEvent(MotionEvent event) {
//	            for (Dibujo cp : lista)
//	                cp.handleOnTouchEvent(event);
//	            return true;
//	  } 
		
		
	public void setOpcion(int num){
		opcion=num;
	}
	
	
	public ArrayList<Dibujo> devolver(){
		
		return pizarra.lista;
	}
	
	public void jugadorNuevo(){
		Bitmap dibujo=BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_delete);
		lista.add(new Dibujo(dibujo));
	}
	
	public void jugadorNuevo2(){
		Bitmap dibujo=BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_dialog_alert);
		lista.add(new Dibujo(dibujo));
	}
	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_pizarra, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case R.id.menu_mover:
	    		pizarra.setOpcion(0);
	    		return true;
	    
	    	case R.id.menu_dibujar:
	            pizarra.setOpcion(1);
	            return true;
	     
	            
	        case R.id.menu_borrar:
	        	pizarra.setOpcion(2);
	            return true;
	            
	        case R.id.menu_deshacer:
	        	pizarra.setOpcion(3);
	        	pizarra.path.reset();
	        	pizarra.setOpcion(0);
	        	pizarra.invalidate();
	        	return true;
	            
	        case R.id.menu_anadir1:
	        	pizarra.setOpcion(4);
	        	pizarra.jugadorNuevo();
	        	pizarra.setOpcion(0);
	        	pizarra.invalidate();
	            return true;
	            
	        case R.id.menu_anadir2:
	        	pizarra.setOpcion(5);
	        	pizarra.jugadorNuevo2();
	        	pizarra.setOpcion(0);
	        	pizarra.invalidate();
	            return true;
	       
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
}
