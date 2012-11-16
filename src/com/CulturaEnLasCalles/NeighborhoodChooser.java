package com.CulturaEnLasCalles;

import java.util.ArrayList;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class NeighborhoodChooser extends Activity {
	
	
	public class Node
	{
		public String mBarrio;
		public String mDescription;
		public Integer mImageResource;
		
	}
	
	//Definimos un array de tipo Node
	private static ArrayList<Node> mArray = new ArrayList<Node>();
	
    /** Called when the activity is first created. */
	String category;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neighborhoodchooser);
        Bundle data=this.getIntent().getExtras();
        if(data!=null)
            category=(data.containsKey("category"))?data.getString("category"):"";
       LinearLayout layout=((LinearLayout)this.findViewById(R.layout.main));
            //http://agendacultural.buenosaires.gob.ar/webservice/response/client.php?Method=GetBarriosListFiltered&IdBarrio=&Descripcion=&OrdenarPor=Descripcion&Orden=ASC&Limit=100&Offset=0
    //        LinearLayout layout=((LinearLayout)this.findViewById(R.id.listaneighborhoods));
            for(int x=0;x<globalData.neighborhoods.length;x++){
            	ButtonWithID aux=new ButtonWithID(this);
            	aux.setText(globalData.neighborhoods[x].getDescription());
            	aux.setresultID(globalData.neighborhoods[x].getId()+"");
            	aux.setInternalName(globalData.neighborhoods[x].getInternalCategoryValue());
            	aux.setOnClickListener(new Button.OnClickListener() 
                {
                    public void onClick(View v) 
                    {
                    	String neighborhood=(String) ((ButtonWithID)v).getInternalName();
                    	Intent TimeChooser=new Intent(v.getContext(),TimeChooser.class);
                    	TimeChooser.putExtra("neighborhood",neighborhood.equalsIgnoreCase("todos")?"":neighborhood);
                    	TimeChooser.putExtra("category",category);
                    	startActivity(TimeChooser);
                    }
                });
            	layout.addView(aux);
            }
        
    }
    
    public void getNeighborhood(View v){
    	String neighborhood=(String) ((Button)v).getText();
    	neighborhood=neighborhood.replace(' ', '-').toLowerCase();
    	Intent TimeChooser=new Intent(this,TimeChooser.class);
    	TimeChooser.putExtra("neighborhood",neighborhood.equalsIgnoreCase("todos")?"":neighborhood);
    	TimeChooser.putExtra("category",category);
    	startActivity(TimeChooser);
    }

}