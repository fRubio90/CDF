package com.example.futbola;



import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class AdaptadorResultados extends ArrayAdapter<String> {

	Activity context;
	   
    AdaptadorResultados(Activity context,String[] datos) {
    super(context, R.layout.resultados, datos);
    this.context = context;
    }
   
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater = context.getLayoutInflater();
    	View item = inflater.inflate(R.layout.resultados, null);
    	
    	final EditText txtCelda =(EditText)item.findViewById(R.id.txtCelda2);
    	txtCelda.setText(this.getItem(position).toString());
  

return(item);
}

//    public String update(int position) {
//    	 String s;
//    	 s= getItem(position);
//    	 notifyDataSetChanged();
//    	 return s;
//    	}
	
	
	
}
