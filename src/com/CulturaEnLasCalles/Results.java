package com.CulturaEnLasCalles;


import java.util.ArrayList;
import java.util.Calendar;




import android.app.ListActivity;

import android.app.ProgressDialog;
import android.content.ComponentName;
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
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends ListActivity {
    /** Called when the activity is first created. */
	
	ListView v;
	String category="";
	String from;
	String to;
	String url;
  
	RSSFeed resultados;
	long milli;
	
	private MyAdapter mAdapter = null;
	
		
	private static ArrayList<RSSItem> mArray = new ArrayList<RSSItem>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    
        Bundle data=this.getIntent().getExtras();
        if(data!=null){
            category=(data.containsKey("category"))?data.getString("category"):"";
            from=(data.containsKey("from"))?data.getString("from"):"";
            to=(data.containsKey("length"))?data.getString("length"):"";
        }
    
        milli=convert(from).getTimeInMillis();
       
       for(int dias=0;dias<Integer.parseInt(to);dias++){
    	   Calendar wantedDay=Calendar.getInstance();
    	   wantedDay.setTimeInMillis(milli);
    	   url="http://agendacultural.buenosaires.gob.ar/static/rssgen.php?";
           if(category!=""){
           	url+="categoria="+category+"&";
           }
     
           if(from!=""){
           	url+="fechaDesde="+wantedDay.get(Calendar.DAY_OF_MONTH)+"-"+(wantedDay.get(Calendar.MONTH))+"-"+wantedDay.get(Calendar.YEAR)+"&";
           }
           if(to!=""){
           	url+="dias=1&";
           }
           url+="limite=20&";
           url=url.substring(0, url.length()-1);
      	
        
        mAdapter = new MyAdapter (this,category);
        new getRSSFeed().execute(); 
         v = (ListView)this.findViewById(R.layout.results);
  
       }
  
    }
  
     private class getRSSFeed extends AsyncTask<Void,Void,Void>
    {  ProgressDialog pd=null;

    
    	protected void onPreExecute()
    	{
    		
    		pd=ProgressDialog.show(Results.this, "Resultados", "Obteniendo resultados");
    	}
		@Override
		protected Void doInBackground(Void... arg0) {
			
			   resultados= new RSSFeed();
	          try
	          {
	        	resultados= resultados.getFeed(url);
			    
			    mArray=(ArrayList<RSSItem>) resultados.getAllItems();
	          
	            try
	            {
	            	Thread.sleep(5000);
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appmenu, menu);
       
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        
    	
        switch (item.getItemId())
        {
        case R.id.menuItem1:
        	
        	Intent intentC = new Intent(Intent.ACTION_EDIT);
        	intentC.setType("vnd.android.cursor.item/event");
        	
        	intentC.putExtra("beginTime", this.milli);
        	
        	intentC.putExtra("allDay", false);
        	intentC.putExtra("endTime", this.milli+24*60*60*1000);
        	
        	intentC.putExtra("title", (mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).getTitle()));
        	this.startActivity(intentC);
        	
           
            return true;
        case R.id.menuItem2:
        	
        	Uri imUri = new Uri.Builder().scheme("imto").authority("gtalk").appendPath("msemken@gmail.com").build();
        	
        	Intent intentG = new Intent(Intent.ACTION_SENDTO, imUri);
      
        	intentG.putExtra(Intent.EXTRA_TEXT, "Texto"); 
        	intentG.putExtra(Intent.EXTRA_SUBJECT,"texto");
        	intentG.putExtra(Intent.EXTRA_STREAM, "Texto");
        	this.startActivity(intentG);
        	/*   Intent i1 = new Intent(Intent.ACTION_MAIN);
        	    i1.setComponent(new ComponentName("com.google.android.talk","com.google.android.talk.SigningInActivity"));
        	    i1.putExtra(Intent.EXTRA_EMAIL, "msemken@gmail.");
        	    
        	    i1.putExtra(Intent.EXTRA_TEXT, "texto");
        	    startActivity(i1);*/ 
            Toast.makeText(this, "Menu opcion 2",1000).show();
            return true;
        case R.id.menuItem3:
        	
        	Posicion posicion;
        	posicion= new Posicion(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).getLink());
        	
        	try
        	{
        
        	}
        	catch (Exception e)
        	{
        		
        	}
        	Intent i = new Intent(this,Ubicacion.class);
       		i.putStringArrayListExtra("Posicion", posicion.getArray());
       		startActivity(i);		
       
            return true;
        case R.id.menuItem4:
            Toast.makeText(this, "Menu opcion 4",1000).show();
            return true;    
        case R.id.menuItem5:
        	
        	Intent browserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(mArray.get(((MyAdapter)this.getListAdapter()).getPoscion()).getLink()));
			startActivity(browserIntent); 
        	Toast.makeText(this, "Menu opcion 5",1000).show();
            return true;    
        default:
            return super.onOptionsItemSelected(item);
            
        }
        
    }
   
    protected void onListItemClick(ListView l, View v, int position, long id)
	{
		
		if (mArray.get(position).getLink()!=null)
		{
			Intent browserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(mArray.get(position).getLink()));
			startActivity(browserIntent);
		}
	}
   
    
    
        public void SaveProfile(View v){
    	SQLHelper sql=new SQLHelper(this, "DB", null, 1);
    	SQLiteDatabase db=sql.getReadableDatabase();
        Cursor response=db.rawQuery("select rowid from Profiles where Category='"+category+"' and Neighborhood=' '", null);
        if(response.getCount()==0){
        	db.close();
	        db=sql.getWritableDatabase();
	        db.execSQL("INSERT INTO Profiles (Category,Neighborhood) VALUES('"+category+"',' ')");
        }
        db.close();
        Toast.makeText(v.getContext(), "Guardado exitoso", Toast.LENGTH_SHORT).show();
        ((Button)v).setEnabled(false);
    }

  
    private Calendar convert(String d){
    	String[] time=d.split("-");
    	Calendar re=Calendar.getInstance();
    	re.set(Integer.parseInt(time[2]),Integer.parseInt(time[1]),Integer.parseInt(time[0]),0,0);
    	
    	if(re.before(Calendar.getInstance())){
    		re=Calendar.getInstance();
        	re.set(Integer.parseInt(time[2]),Integer.parseInt(time[1]),Integer.parseInt(time[0]));
    	}
    	
    	return re;
    }
    
    
    public static class MyAdapter extends BaseAdapter
	{

		private Context mContext;
		private String tipo;		
		private int posActual;
		private Button anterior;
		public MyAdapter(Context c,String t)
		{
			mContext = c;
			tipo=t;
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
				
				view = inflater.inflate(R.layout.results, null);
			}
			else{
				//usa convertView si está disponible
				view = convertView;
			}
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
                	}                	
                	pos.setBackgroundColor(Color.BLUE);            
                	setButton(pos);               	
                	setPosicion(Integer.parseInt(pos.getText().toString()));
                	
                }
            });
		
			TextView tTitle = (TextView) view.findViewById(R.id.title);
			tTitle.setText(mArray.get(position).getTitle());			
			tTitle.setVisibility(0);
		
			
			TextView tDescription = (TextView) view.findViewById(R.id.description);
			if (mArray.get(position).getLink()!=null)
			{
				tDescription.setText("ver Detalles");
				
			}
			else
				if (mArray.get(position).getDescription()!="<")
					tDescription.setText(mArray.get(position).getDescription());
			
			tDescription.setVisibility(0);
		
			return view;
		
		}
	 }	
	 
    }

              
    
    


