package com.CulturaEnLasCalles;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Actividades extends ListActivity {

	
	ArrayActividad resultados;
	ListView v;
	String url;
	String barrio;
	
	private MyAdapter mAdapter = null;
	
	private static ArrayList<regActividad> mArray;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        
        
       
        Bundle data=this.getIntent().getExtras();
        if(data!=null){
            barrio=(data.containsKey("barrio"))?data.getString("barrio"):"";            
        }
        
        url="http://agendacultural.buenosaires.gob.ar/webservice/response/client.php?Method=GetEventosByIdBarrios&IdBarrios="+barrio;
        mAdapter = new MyAdapter (this);
        new getRSSFeed().execute(); 
         v = (ListView)this.findViewById(R.layout.actividades);
         
         
  
       }
  
    
  
     private class getRSSFeed extends AsyncTask<Void,Void,Void>
     {  ProgressDialog pd=null;

    
    	protected void onPreExecute()
    	{
    		
    		pd=ProgressDialog.show(Actividades.this, "Resultados", "Obteniendo resultados");
    	}
		@Override
		protected Void doInBackground(Void... arg0) {
			
			   
	          try
	          {
	        	  resultados= new ArrayActividad(url);
			    
			    mArray=(ArrayList<regActividad>) resultados.getArray();
	          
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
    	//String barrio=((String) mArray.get(position).toLowerCase());
    	//Intent NeighborhoodChooser=new Intent(v.getContext(),NeighborhoodChooser.class);
    	Intent Actividad=new Intent(v.getContext(),Actividad.class);
    	Actividad.putExtra("titulo", mArray.get(position).get_Titulo() );
    	Actividad.putExtra("Descripcion", mArray.get(position).get_Descripcion());
    	//NeighborhoodChooser.putExtra("category",category.equalsIgnoreCase("todos")?"":category); 
    //	Actividades.putExtra("barrio",barrio.equalsIgnoreCase("todos")?"":barrio);	
		
			startActivity(Actividad);
		
	}
   
  
    
    public static class MyAdapter extends BaseAdapter
	{

		private Context mContext;
		private Button anterior;
		private int posActual;
		
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
		
		
		public Button getButton()
		{
			return anterior;
		}
		public void setButton(Button b)
		{
			anterior=b;
		}
		public int getPoscion()
		{
			return posActual;
		}
		public void setPosicion(int p)
		{
			posActual=p;
		}
		
		
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = null;
			
			if (convertView == null){
				//Crea una nueva vista
				LayoutInflater inflater = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				view = inflater.inflate(R.layout.actividades, null);
			}
			else{
				//usa convertView si está disponible
				view = convertView;
			}
		/*	final Button pos = (Button) view.findViewById(R.id.botonL);
			pos.setBackgroundColor(Color.LTGRAY);
			pos.setText(String.valueOf(position));		
			//pos.setTextColor(Color.LTGRAY);
			pos.setTextColor(Color.TRANSPARENT);
			
			pos.setOnClickListener(new Button.OnClickListener() 
            {
                public void onClick(View v) 
                {
                	if (getButton()!=null)
                	{
                		getButton().setBackgroundColor(Color.LTGRAY);                       		
                	}                	
                	pos.setBackgroundColor(Color.BLUE);            
                	setButton(pos);               	
                	setPosicion(Integer.parseInt(pos.getText().toString()));
                	
                }
            });
		*/
			TextView tTitle = (TextView) view.findViewById(R.id.titulo);
			tTitle.setText(mArray.get(position).get_Titulo());			
			tTitle.setVisibility(0);
		
			
			TextView tResumen = (TextView) view.findViewById(R.id.Resumen);
			tResumen.setText(mArray.get(position).get_Resumen());			
			tResumen.setVisibility(0);
		
			TextView tFechas = (TextView) view.findViewById(R.id.Fechas);
			tFechas.setText(mArray.get(position).get_FechaInicio() + " - "+mArray.get(position).get_FechaFin());			
			tFechas.setVisibility(0);
			if (mArray.get(position).get_Hora()!=null&&mArray.get(position).get_Minutos()!=null)
			{
			TextView tHora = (TextView) view.findViewById(R.id.Hora);
			tHora.setText(mArray.get(position).get_Hora() + " - "+mArray.get(position).get_Minutos());			
			tHora.setVisibility(0);
			}
			return view;
		
		}

		
		}
	 }	
	 
	

