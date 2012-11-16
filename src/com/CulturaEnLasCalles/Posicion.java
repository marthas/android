package com.CulturaEnLasCalles;


import java.util.ArrayList;

public class Posicion {
	
	
	RegPosicion _posicion;
	public Posicion(String url)
	{
		LeerUrl datos=new LeerUrl(url);
		try
		{
		String cadPos=datos.parser();
		ObtenerDatos(cadPos);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public Posicion (ArrayList<String> datos)
	{
		_posicion= new RegPosicion();
		_posicion.setLatitud(datos.get(0));
		_posicion.setLongitud(datos.get(1));
		_posicion.setDomicilio(datos.get(2));
		_posicion.setBarrio(datos.get(3));
		_posicion.setLugar(datos.get(4));
		
	}
	private void ObtenerDatos(String datos)
	{
		_posicion= new RegPosicion();
		_posicion.setLatitud(datos.substring(datos.indexOf("(",datos.indexOf("new google.maps.LatLng"))+1,datos.indexOf(",",datos.indexOf(("new google.maps.LatLng"))-1)));
		_posicion.setLongitud(datos.substring(datos.indexOf(",",datos.indexOf("new google.maps.LatLng"))+1,datos.indexOf(")",datos.indexOf(("new google.maps.LatLng"))-1)));
		_posicion.setDomicilio(datos.substring(datos.indexOf("<p>",datos.indexOf("<div class=textos>"))+3,datos.indexOf("</p>",datos.indexOf(("<div class=textos>"))-1)));		
		_posicion.setLugar(datos.substring(datos.indexOf(">",datos.indexOf("<div class=textos>"))+1,datos.indexOf("<p>",datos.indexOf(("<div class=textos>"))-1)));
		_posicion.setBarrio(datos.substring(datos.indexOf("<p>",datos.indexOf("<p>")+3)+3,datos.indexOf("</p>",datos.indexOf("</p>")+3)));
	}
	public RegPosicion getPosicion()
	{
		return _posicion;
	}

	public ArrayList <String> getArray()
	{
	   ArrayList <String> datos = new ArrayList<String>();
	   datos.add(_posicion.getLatitud());
	   datos.add(_posicion.getLongitud());
	   datos.add(_posicion.getDomicilio());
	   datos.add(_posicion.getBarrio());
	   datos.add(_posicion.getLugar());
	   return datos;	   
		
	}
	
	
}
