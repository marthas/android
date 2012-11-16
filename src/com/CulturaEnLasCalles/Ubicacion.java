package com.CulturaEnLasCalles;
import java.util.List;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;



public class Ubicacion extends MapActivity{
	private MapView mapview = null;
	private MapController mapControl = null;
	
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        
        
                
        mapview = (MapView) findViewById(R.id.myMapView);
        
        mapview.setBuiltInZoomControls(true);
        mapview.setClickable(true);
        
        
        mapControl = mapview.getController();
        
        // Obtain location from intents
        
        Intent i =this.getIntent();
        Posicion posicion= new Posicion(i.getStringArrayListExtra("Posicion"));
        Double latitude = Double.parseDouble(posicion.getPosicion().getLatitud());
        Double longitude = Double.parseDouble(posicion.getPosicion().getLongitud());
      
        
        Location mylocation = new Location("My provider");
        mylocation.setLatitude(latitude);
        mylocation.setLongitude(longitude);
        
        refreshMap(mylocation,posicion);
        
    }

   	
	public void refreshMap(Location location,Posicion posicion)
	{
		// TODO Auto-generated method stub
		if (location == null)
		{
			Toast.makeText(getBaseContext(),
						   "Ezin da eskuratu lokalizazioa!",
						   Toast.LENGTH_LONG).show();
			
			return;
		}
		
		GeoPoint geopoint = new GeoPoint ( (int) (location.getLatitude() * 1000000),
										   (int) (location.getLongitude() * 1000000));
		
		mapControl.setZoom(15);
		mapControl.animateTo(geopoint);
		
		MapaOverlay myMapOver = new MapaOverlay(getResources().getDrawable(R.drawable.drawingpin), 
				posicion.getPosicion().getLugar()+ ","+posicion.getPosicion().getDomicilio() + "  " +posicion.getPosicion().getBarrio()  ,
				 geopoint);
		

		final List<Overlay> overlays = mapview.getOverlays();
		overlays.clear();

		overlays.add(myMapOver);


		//mapview.setSatellite(true);
		//mapview.setBuiltInZoomControls(true);


		mapview.setClickable(true);    			

	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}


}
