package com.CulturaEnLasCalles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import android.widget.TextView;

public class Libros extends ListActivity {
 //String datos="";
 
 public class Node
	{
		public String nombre;
		public String ubicacion;
		public String latitud;
		public String longitud;
		public String barrio;
		public String tematica;
		public String telefono;
		public String email;
		public String web;
		
	}
	
	//Definimos un array de tipo Node
	private static ArrayList<Node> mArray = new ArrayList<Node>();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.libros);   
        setData();
        
        MyAdapter mAdapter = new MyAdapter (this);
       setListAdapter (mAdapter);
        
    
            	
      /* 	SQLHelperLibreria sql=new SQLHelperLibreria(this, "DB", null, 1);
            	
            	SQLiteDatabase db;
     
            	
            	
           	db=sql.getWritableDatabase();
            	sql.onUpgrade(db, 1, 1);
            	String linea;
            	try {
            		

                	
            		InputStream in = getAssets().open("librerias.csv");
            		if (in != null) {
            		InputStreamReader input = new InputStreamReader(in);
            		BufferedReader buffreader = new BufferedReader(input);
            		while ((linea = buffreader.readLine()) != null) {
            	//	datos=datos+linea.toString();
            		String vec[]=linea.split(",");
            		
            	   if (vec.length >10)
            	   {
            	      vec[1]+=","+vec[2];
            	      for(int i=2;i<vec.length-2;i++)
            	         vec[i]=vec[i+1];
            		}
            		if (vec.length>=9)
            			db.execSQL("INSERT INTO Librerias (nombre,ubicacion,latitud,longitud,barrio,tematica,telefono,email,web) VALUES('"+vec[0]+"','"+vec[1]+"','"+vec[2].substring(1)+"','"+vec[3].substring(0,vec[3].length()-2)+"','"+vec[4]+"','"+vec[5]+"','"+vec[6]+"','"+vec[7]+"','"+vec[8]+"')");
            		else
            			if (vec.length>=8)
            				db.execSQL("INSERT INTO Librerias (nombre,ubicacion,latitud,longitud,barrio,tematica,telefono,email,web) VALUES('"+vec[0]+"','"+vec[1]+"','"+vec[2].substring(1)+"','"+vec[3].substring(0,vec[3].length()-2)+"','"+vec[4]+"','"+vec[5]+"','"+vec[6]+"','"+vec[7]+"','"+vec[7]+"')");
            		}
            		in.close();
            		}
            		} catch (IOException e) {
            		e.printStackTrace();
            		}
            
            	
            	
	}	*/
            
            
           
            
	} 
	
       public boolean onCreateOptionsMenu(Menu menu)
       {
           MenuInflater menuInflater = getMenuInflater();
           menuInflater.inflate(R.menu.libmenu, menu);
          
           return true;
       }
    
       @Override
       public boolean onOptionsItemSelected(MenuItem item)
       {
           
       	
           switch (item.getItemId())
           {
          
           case R.id.menuItem1:
           	
        	   Intent em = new Intent(android.content.Intent.ACTION_SEND);
        	   em.setType("plain/text");
        	    
        	   em.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).email.toString())});
        	   
        	   em.putExtra(android.content.Intent.EXTRA_SUBJECT, "Consulta");
        	   em.putExtra(android.content.Intent.EXTRA_TEXT, "Texto de mi correo");
        	    
        	   em.putExtra(Intent.EXTRA_STREAM,
        	       Uri.parse("file://"+Environment.getExternalStorageDirectory()+"/archivo.txt"));
        	    
        	   startActivity(Intent.createChooser(em, "Enviar correo electrónico"));
        	   
               return true;
               
           case R.id.menuItem2:
        	   Intent browserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).web)));
  				startActivity(browserIntent);
        	  
               return true;
           case R.id.menuItem3:
           	
           	Posicion posicion;
           	ArrayList <String>datos= new ArrayList<String>();
           	datos.add(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).latitud);
           	datos.add(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).longitud);
           	datos.add(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).ubicacion);
           	datos.add(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).barrio);
           	datos.add(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).nombre);
           	posicion= new Posicion(datos);
           	
           	Intent i = new Intent(this,Ubicacion.class);
          		i.putStringArrayListExtra("Posicion", posicion.getArray());
          		startActivity(i);		
          
               return true;
           case R.id.menuItem4:
               Toast.makeText(this, "Menu opcion 3",1000).show();
               return true;    
           default:
               return super.onOptionsItemSelected(item);
               
           }
           
       }
 
private void setData() 
	{
		// TODO Auto-generated method stub

		mArray.clear();
		
		//Curso Tk045, 1 en la lista
		
		
		SQLHelperLibreria sql=new SQLHelperLibreria(this, "DB", null, 1);
        SQLiteDatabase db=sql.getReadableDatabase();
        Cursor response=db.rawQuery("select * from Librerias order by barrio desc", null);
        response.moveToFirst();
        response.moveToNext();
   
        do{
       
        	Node mynode = new Node();
		    mynode.nombre=String.valueOf(response.getString(0));
		    mynode.ubicacion=String.valueOf(response.getString(1));
		    mynode.latitud=String.valueOf(response.getString(2));
		    mynode.longitud=String.valueOf(response.getString(3));
		    mynode.barrio=String.valueOf(response.getString(4));
		    mynode.tematica=String.valueOf(response.getString(5));
		    mynode.telefono=String.valueOf(response.getString(6));		
		    mynode.email=String.valueOf(response.getString(7));
		    mynode.web=String.valueOf(response.getString(8));		    
			mArray.add(mynode);
	  	            
        	response.moveToNext();
        }while(!response.isAfterLast());
        db.close();
        			
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
				
				view = inflater.inflate(R.layout.libros, null);
			}
			else{
				//usa convertView si está disponible
				view = convertView;
			}
			
			//Rellenamos los recursos de la vista: una imagen y dos textos
			final Button pos = (Button) view.findViewById(R.id.botonL);
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
              //  		getButton().setTextColor(Color.LTGRAY);                		
                	}
                	
                	pos.setBackgroundColor(Color.BLUE);
                //	pos.setTextColor(Color.BLUE);
                	setButton(pos);               	
                	setPosicion(Integer.parseInt(pos.getText().toString()));
                	
                }
            });
	
			TextView tTitle = (TextView) view.findViewById(R.id.libre1);
			if (mArray.get(position).barrio.indexOf("-")!=-1)
				tTitle.setText(mArray.get(position).nombre +"\n" +mArray.get(position).ubicacion + "\n"+mArray.get(position).tematica);
			else
				tTitle.setText(mArray.get(position).nombre +"\n " +mArray.get(position).ubicacion + " "+mArray.get(position).barrio);
			
			TextView tDescripcion = (TextView) view.findViewById(R.id.libre2);
			if (mArray.get(position).barrio.indexOf("-")!=-1)
				tDescripcion.setText(mArray.get(position).telefono+ "\n"+ mArray.get(position).web );
			else
				tDescripcion.setText(mArray.get(position).tematica+ "\n"+ mArray.get(position).web );
			   			
			
			return view;
			
		}

	

}
}


