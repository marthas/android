package com.CulturaEnLasCalles;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MenuP extends TabActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_p);
        TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Espectaculos");
        photospec.setIndicator("Espectaculos", getResources().getDrawable(R.drawable.icon_espectaculos));
        Intent photosIntent = new Intent(this, Espectaculos.class);
        photospec.setContent(photosIntent);
        
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Perfiles");
        // setting Title and Icon for the Tab
        songspec.setIndicator("Actividades", getResources().getDrawable(R.drawable.icon_perfiles));
        Intent songsIntent = new Intent(this, Perfiles.class);
        songspec.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("Libros");
        videospec.setIndicator("Libros", getResources().getDrawable(R.drawable.icon_libros));
        Intent videosIntent = new Intent(this, Libros.class);
        videospec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
        
        
	}
	
/*	public void NewSearch(View v)
	{
    	Intent newWindow=new Intent(this,CulturaEnLasCallesActivity.class);
    	startActivity(newWindow);
	}
	
	public void GetProfile(View v)
	{
    	Intent newWindow=new Intent(this,ChooseProfile.class);
    	startActivity(newWindow);
	}
	

	
	
	*/
}
