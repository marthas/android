package com.CulturaEnLasCalles;

import java.util.ArrayList;

public class Barrios {
	
	
	ArrayList <String> _barrios;
	public Barrios(String url)
	{
		LeerUrl datos=new LeerUrl(url);
		_barrios=new ArrayList<String>();
		try
		{
		String resultados=datos.datos();
		ObtenerDatos(resultados);
		
		}
		catch(Exception e)
		{
			
		}
		
		
	}
	
		private void ObtenerDatos(String datos)
	{String barrio;
		while (datos.indexOf("<NombreUrl>")!=-1)		
		{
			barrio=datos.substring(datos.indexOf("<NombreUrl>")+20,datos.indexOf("</NombreUrl>")-3);
			barrio+=","+datos.substring(datos.indexOf("<IdBarrio>")+10,datos.indexOf("</IdBarrio>")-1);
		    _barrios.add(barrio);
		    datos=	datos.substring(datos.indexOf("</Item>")+7);
		}
	}
	
	public ArrayList <String> getArray()
	{
	   return _barrios;	   
		
	}


}
