package com.example.futbola;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class AnadirJugadores extends Activity {

	private static final int SELECT_PHOTO = 100;
	private ImageView prueba;
	private Bitmap bit;
	private byte [] con;
	
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_anadir_jugadores);
	        
	        prueba=(ImageView)findViewById(R.id.ImgFoto);
	        Button btnImagen=(Button)findViewById(R.id.btnImagen);
	        Button btnGuardar=(Button)findViewById(R.id.btnGuardarJugador);
	        
	        
	        btnImagen.setOnClickListener(new OnClickListener(){
				  
				@Override
				public void onClick(View v) {
					
			        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			        photoPickerIntent.setType("image/*");
			        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
					
				}
	        });
	       

	        btnGuardar.setOnClickListener(new OnClickListener(){
				  
				@Override
				public void onClick(View v) {
					EditText txtNombre=(EditText)findViewById(R.id.txtNombre);
					
					if(txtNombre.getText().toString().equals("")){
						TextView lblDatos=(TextView)findViewById(R.id.lblDatos);
						lblDatos.setText("Sartu Jokalariaren izena");
						lblDatos.setTextColor(Color.RED);
					}
					else{
					gorde();
					}
					//lortu();
					
//					EditText prueba=(EditText)findViewById(R.id.txtNombre);
//			          if(con==null)
//			        	  prueba.setText("ERROR");
//			          else
//			        	  prueba.setText("EXITO");
					
				}
	        });
				
	        
	        //DAtu Basea////////////////////////////
//	        JugadoresSQLiteHelper jdbh =
//	                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
//	     
//	            SQLiteDatabase db = jdbh.getWritableDatabase();
//	            Cursor c=null;
//	     
//	            //Si hemos abierto correctamente la base de datos
//	            if(db != null)
//	            {
//		          EditText prueba=(EditText)findViewById(R.id.txtNombre);
//		          if(con==null)
//		        	  prueba.setText("ERROR");
//		          else
//		        	  prueba.setText("EXITO");
	            	
//	            	c =db.rawQuery("SELECT * FROM Jugadores",null);
//	            	c.moveToFirst();
//	            	
//	            	byte[] blob=c.getBlob(5);
//	            	
	            	
//	            	Bitmap bmp=BitmapFactory.decodeByteArray(blob,0,blob.length);
//	            	prueba.setImageBitmap(bmp);
//	            	
	            	
//	            	db.execSQL("DELETE FROM JUGADORES WHERE Nombre=nombre");
//	            	ContentValues cv = new  ContentValues();
//	            	cv.put("Nombre",    "nombre");
//	                cv.put("Apellido", "apellido");
//	                cv.put("Edad",   18);
//	                cv.put("email",   "EMAIL");
//	                
//	               
//               	
//	                cv.put("Imagen", con);
//	                db.insert("Jugadores", null, cv );

		          	  //prueba.setText("aupa!!");
//		          	  if (blob==null)
//		          		prueba.setText("ERROR");
//		          	  else
//		          	  prueba.setText("aupa");
	            	
//	            }
	        
	        
	        
	    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	   try{
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case SELECT_PHOTO:
	        if(resultCode == RESULT_OK){  
	            Uri selectedImage = imageReturnedIntent.getData();
	            bit=decodeUri(selectedImage);
	            prueba.setImageBitmap(bit);

	            setIrudia(bit);
	            //InputStream imageStream = getContentResolver().openInputStream(selectedImage);
	            //Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
	        }
	    }
	    if (resultCode == RESULT_CANCELED) {    
	        finish();
	    }
	   }
	    catch(FileNotFoundException e){
	    	
	    }
	}
	
	
	private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 100;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
               || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }
	
	public void setIrudia(Bitmap irudia){
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        irudia.compress(Bitmap.CompressFormat.PNG, 0, stream);
        con = stream.toByteArray();
//        EditText prueba2=(EditText)findViewById(R.id.txtApellido);
//        if(con==null)
//      	  prueba2.setText("ERROR");
//        else
//      	  prueba2.setText("EXITO");
        
	}
	
	public void gorde(){
		
		 JugadoresSQLiteHelper jdbh =
	                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
	     
	            SQLiteDatabase db = jdbh.getWritableDatabase();
	            Cursor c=null;
	     
	            //Si hemos abierto correctamente la base de datos
	            if(db != null){
		
//		db.execSQL("DELETE FROM JUGADORES WHERE Nombre=nombre");
		
		//Creamos las variables
		
		EditText txtNombre=(EditText)findViewById(R.id.txtNombre);
        EditText txtApellido=(EditText)findViewById(R.id.txtApellido);
        EditText txtEdad=(EditText)findViewById(R.id.txtEdad);
        EditText txtTelefono=(EditText)findViewById(R.id.txtTelefono);
        EditText txtEmail=(EditText)findViewById(R.id.txtEmail);

    	ContentValues cv = new  ContentValues();
    	//NULL autoincrement betetzeko
    	cv.putNull("Codigo");
    	//if batzuk jarriko doguz arazoak ekiditzeko
    	//Guardar ez zen agertu beharko, derrigorrezko datu batzuk sartu arte EGITEKO!!

    	//cv.put("Nombre",txtNombre.getText().toString() );
    	cv.put("Nombre",txtNombre.getText().toString());
    	cv.put("Apellido", txtApellido.getText().toString());
    	
    	if(txtEdad.getText().toString().equals(""))
    		cv.put("Edad", 0);
    	else
    		cv.put("Edad", Integer.parseInt(txtEdad.getText().toString()));
    	
    	if(txtTelefono.getText().toString().equals(""))
    		cv.put("Telefono", 0);
    	else
    		cv.put("Telefono",Integer.parseInt(txtTelefono.getText().toString()));
    	
        cv.put("email",  txtEmail.getText().toString());
     if(con!=null)
   	    cv.put("Imagen", con);
     else
    	 cv.putNull("Imagen");
        db.insert("Jugadores", null, cv );
        db.close();
        mezua();
	
	            }
	            
	}
	
	public void lortu(){
		JugadoresSQLiteHelper jdbh =
                new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
     
            SQLiteDatabase db = jdbh.getWritableDatabase();
            Cursor c=null;
            
            if(db != null){
            c =db.rawQuery("SELECT * FROM Jugadores",null);
        	
            c.moveToFirst();
        	
        	byte[] blob=c.getBlob(5);
        	
        	
        	Bitmap bmp=BitmapFactory.decodeByteArray(blob,0,blob.length);
        	prueba.setImageBitmap(bmp);
            }
	}
	
	public void mezua(){
		Intent i = new Intent(this, Mezua.class);
		startActivityForResult(i, 1);
	}
	

	
}