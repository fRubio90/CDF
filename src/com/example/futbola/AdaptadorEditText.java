package com.example.futbola;


import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;


public class AdaptadorEditText extends BaseAdapter{
	
	
	private Context myContext;
	private String[]nombres;
	private EditText[] myCells;
	
	public AdaptadorEditText(Context c, String[] nombres, int num){
	
		myContext=c;
		this.nombres=nombres;
		myCells=new EditText[num];
		Log.i("parada","el valor es: "+num);
	}
	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nombres.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return myCells[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EditText cell;
		  if (myCells[position] == null)
		     {

		   cell = myCells[position] =new EditText(myContext);
		   cell.setText(nombres[position]);
		   cell.setInputType(2);//2=NUMBER
		   cell.setHeight(110);
		  		   
		        }
		     else
		     {
		      cell = myCells[position];
		     }
		     return cell;
		 }
		
	
public String update(int position){
	
	EditText o=(EditText)getItem(position);	
	Log.i("parada", "reult: "+o.getClass());
	Log.i("parada", "reult: "+o.getText());
	
	return o.getText().toString();
	}

}

//	  private Context mContext;
//	  	String[]nombres;
//	
//	  	public AdaptadorEditText(Context context, String[] nombres){
//	  		
//            super();
//            mContext=context;
//            this.nombres=nombres;
//	  		
//	  	}
//
//	
//	
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return nombres.length;
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View view, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		
//		 LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//         view = inflater.inflate(R.layout.resultados, null);
//		
//         Final EditText celda=(EditText)view.findViewById(R.id.txtCelda2);
//         
//         celda.setText(nombres[position]);
//
//		
//		
//		return view;
//	}
//	
//	
//	public String update(int position){
//	
//		
//	Log.i("parada", "reult: "+this.getCount());
//	
//	return "aupa";
//	}
//}