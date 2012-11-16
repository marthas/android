package com.CulturaEnLasCalles;

import java.util.ArrayList;


import android.app.Activity;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class Espectaculos extends ListActivity {

	
        
        //setContentView(R.layout.espectaculos);
    	private MyAdapter mAdapter = null;
    	
    	//definimos una estructura de datos
    	public class Node
    	{
    		public String idCat;
    		public String mCategory;
    		
    		
    	}
    	
    	//Definimos un array de tipo Node
    	private static ArrayList<Node> mArray = new ArrayList<Node>();
    	
    	
    	@Override
        public void onCreate(Bundle savedInstanceState)
    	{
            super.onCreate(savedInstanceState);
            
            setData();
            
            mAdapter = new MyAdapter (this);
            setListAdapter (mAdapter);
        }
    	
    	protected void onListItemClick(ListView l, View v, int position, long id)
    	{
    		//Crea un nuevo intent para llamar a otra actividad
    		
    		String category=((String) mArray.get(position).mCategory).toLowerCase();
        	//Intent NeighborhoodChooser=new Intent(v.getContext(),NeighborhoodChooser.class);
        	Intent TimeChooser=new Intent(v.getContext(),TimeChooser.class);
        	//NeighborhoodChooser.putExtra("category",category.equalsIgnoreCase("todos")?"":category); 
        	TimeChooser.putExtra("category",category.equalsIgnoreCase("todos")?"":category);
        	startActivity(TimeChooser);
    		//Toast.makeText(this, mArray.get(position).mCategory, Toast.LENGTH_LONG).show();
    	}
    	
    	private void setData() 
    	{
    		// TODO Auto-generated method stub
 
    		mArray.clear();
    		
    		//Curso Tk045, 1 en la lista
    		
    		for(int i=0;i<globalData.categories.length;i++)
    		{	Node mynode = new Node();
    		    mynode.idCat=String.valueOf(globalData.categories[i].getId());
    		    mynode.mCategory=globalData.categories[i].getDescription();
    			mArray.add(mynode);
    		}	
    		
    					
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
    				
    				view = inflater.inflate(R.layout.espectaculos, null);
    			}
    			else{
    				//usa convertView si está disponible
    				view = convertView;
    			}
    			
    			//Rellenamos los recursos de la vista: una imagen y dos textos
    			ImageView img = (ImageView) view.findViewById(R.id.imageE);
    		
    				
    			img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cine));
    			
    			TextView tTitle = (TextView) view.findViewById(R.id.categoria);
    			tTitle.setText(mArray.get(position).mCategory);
    			
    			   			
    			
    			return view;
    			
    		}
    		
    	}	      
	
 	
	}

