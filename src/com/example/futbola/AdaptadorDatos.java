package com.example.futbola;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class AdaptadorDatos extends ArrayAdapter<String>{
	

Activity context;
    
    AdaptadorDatos(Activity context, String[] datos) {
    super(context, R.layout.datos, datos);
    this.context = context;
    }
   
    public View getView(int position, View convertView, ViewGroup parent) {
LayoutInflater inflater = context.getLayoutInflater();
View item = inflater.inflate(R.layout.datos, null);

TextView txtCelda =(TextView)item.findViewById(R.id.lblCabeceras);
txtCelda.setText(this.getItem(position).toString());

    

return(item);
}

}
