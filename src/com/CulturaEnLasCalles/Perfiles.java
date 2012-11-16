package com.CulturaEnLasCalles;

import java.util.ArrayList;
import java.util.Calendar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Perfiles extends ListActivity {

	Barrios resultados;
	ListView v;
	String url;
	
	private MyAdapter mAdapter = null;
	
	private static ArrayList<String> mArray;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        
        url="http://agendacultural.buenosaires.gob.ar/webservice/response/client.php?Method=GetBarriosListFiltered&IdBarrio=&Descripcion=&OrdenarPor=Descripcion&Orden=ASC&Limit=100&Offset=0";
       
        mAdapter = new MyAdapter (this);
        new getRSSFeed().execute(); 
         v = (ListView)this.findViewById(R.layout.results);
  
       }
  
    
  
     private class getRSSFeed extends AsyncTask<Void,Void,Void>
     {  ProgressDialog pd=null;

    
    	protected void onPreExecute()
    	{
    		
    		pd=ProgressDialog.show(Perfiles.this, "Resultados", "Obteniendo resultados");
    	}
		@Override
		protected Void doInBackground(Void... arg0) {
			
			   
	          try
	          {
	        	  resultados= new Barrios(url);
			    
			    mArray=(ArrayList<String>) resultados.getArray();
	          
	            try
	            {
	            	Thread.sleep(3000);
	            }
	            catch(InterruptedException e)
	            {
	            	e.printStackTrace();
	            }
	          }
	          catch(Exception e)
	          {
	        	  e.printStackTrace();
	          }
		
			return null;
		}
    	
		@Override
		protected void onPostExecute(Void unused)
		{
			pd.dismiss();
			setListAdapter(mAdapter);
		}
    	
    
     }
 
   
        
   
    protected void onListItemClick(ListView l, View v, int position, long id)
	{
    	String barrio=((String) mArray.get(position).substring(mArray.get(position).indexOf(",")+1));
    	//Intent NeighborhoodChooser=new Intent(v.getContext(),NeighborhoodChooser.class);
    	Intent Actividades=new Intent(v.getContext(),Actividades.class);
    	//NeighborhoodChooser.putExtra("category",category.equalsIgnoreCase("todos")?"":category); 
    	Actividades.putExtra("barrio",barrio.equalsIgnoreCase("")?"":barrio);	
		
			startActivity(Actividades);
		
	}
   
  
    
    public static class MyAdapter extends BaseAdapter
	{

		private Context mContext;
		
		public MyAdapter(Context c)
		{
			mContext = c;
			
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return mArray.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mArray.get(position);
		}

		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = null;
			
			if (convertView == null){
				//Crea una nueva vista
				LayoutInflater inflater = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				view = inflater.inflate(R.layout.perfiles, null);
			}
			else{
				//usa convertView si está disponible
				view = convertView;
			}
		
			TextView tBarrio = (TextView) view.findViewById(R.id.barrio);
			tBarrio.setText(mArray.get(position).substring(0,mArray.get(position).indexOf(",")));			
			tBarrio.setVisibility(0);
		
			
		
		
			return view;
		
		}
	 }	
	 
}
