package com.example.futbola;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditarJugadores extends Activity {

	private static final int SELECT_PHOTO = 100;
	String kodigoa;
	private byte [] con;


	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_editar_jugadores);
	        
	        Bundle b=new Bundle();
	        b=this.getIntent().getExtras();
	        kodigoa=b.getString("codigo");

	        datuakLortu();
	        Log.i("pa","kodigoa"+kodigoa);

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
					gorde();
					mezua();

					
				}
	        });
	        

	 }

public void datuakLortu(){
	JugadoresSQLiteHelper jdbh =
            new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
 
	String[] izenak=new String[1];
	
        SQLiteDatabase db = jdbh.getReadableDatabase();
        Cursor c=null;
                    
        if(db != null){

        	String[] parametro=new String[1];
        	parametro[0]=kodigoa;
        	c =db.rawQuery("SELECT * FROM Jugadores WHERE codigo=?",parametro);

    		EditText txtNombre=(EditText)findViewById(R.id.txtNombre);
            EditText txtApellido=(EditText)findViewById(R.id.txtApellido);
            EditText txtEdad=(EditText)findViewById(R.id.txtEdad);
            EditText txtTelefono=(EditText)findViewById(R.id.txtTelefono);
            EditText txtEmail=(EditText)findViewById(R.id.txtEmail);
            ImageView imagen=(ImageView)findViewById(R.id.imgArgazkia);
                    	
    	
        	if(c.moveToFirst()){
        		int i=0;
        		do{
        			txtNombre.setText(c.getString(1));
        			txtApellido.setText(c.getString(2));
        			txtEdad.setText(c.getString(3));
        			txtTelefono.setText(c.getString(4));
        			txtEmail.setText(c.getString(5));
    	        	con=c.getBlob(6);
        			
        			//byte[] blob=c.getBlob(6);
    	        	if(con!=null){
    	        	Bitmap bmp=BitmapFactory.decodeByteArray(con,0,con.length);
    	        	imagen.setImageBitmap(bmp);
    	        	}

        		}while(c.moveToNext());
            	db.close();
        	}
        }

}


@Override
protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
   try{
	super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

    switch(requestCode) { 
    case SELECT_PHOTO:
        if(resultCode == RESULT_OK){  
            Uri selectedImage = imageReturnedIntent.getData();
            Bitmap bit=decodeUri(selectedImage);
            ImageView imagen=(ImageView)findViewById(R.id.imgArgazkia);
            imagen.setImageBitmap(bit);

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

    
}

public void gorde(){
	JugadoresSQLiteHelper jdbh =
            new JugadoresSQLiteHelper(this, "DBJugadores", null, 1);
 
        SQLiteDatabase db = jdbh.getWritableDatabase();
        Cursor c=null;
 
        //Si hemos abierto correctamente la base de datos
        if(db != null){

	//db.execSQL("DELETE FROM JUGADORES WHERE Nombre=nombre");
	
	//Creamos las variables
	
	EditText txtNombre=(EditText)findViewById(R.id.txtNombre);
	EditText txtApellido=(EditText)findViewById(R.id.txtApellido);
	EditText txtEdad=(EditText)findViewById(R.id.txtEdad);
	EditText txtTelefono=(EditText)findViewById(R.id.txtTelefono);
	EditText txtEmail=(EditText)findViewById(R.id.txtEmail);
	
	ContentValues cv = new  ContentValues();
	//NULL autoincrement betetzeko
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
	
	   cv.put("Imagen", con);
	   String[]argumento=new String[1];
	   argumento[0]=kodigoa;
	   db.update("Jugadores",cv,"Codigo=?",argumento);
	   db.close();

        }
}


public void mezua(){
	Intent i = new Intent(this, Mezua.class);
	startActivityForResult(i, 1);
}




}