package com.CulturaEnLasCalles;

import java.util.ArrayList;

public class ArrayActividad {
	
	ArrayList <regActividad> _actividades;
	public ArrayActividad(String url)
	{
		LeerUrl datos=new LeerUrl(url);
		_actividades=new ArrayList<regActividad>();
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
	{regActividad actividad;
		while (datos.indexOf("<Titulo>")!=-1)		
		{
			actividad=new regActividad();
			actividad.set_Titulo(datos.substring(datos.indexOf("<Titulo>")+17,datos.indexOf("</Titulo>")-3));
			actividad.set_Descripcion(datos.substring(datos.indexOf("<Descripcion>")+22,datos.indexOf("</Descripcion>")-3));
			actividad.set_Resumen(datos.substring(datos.indexOf("<Resumen>")+18,datos.indexOf("</Resumen>")-3));
			actividad.set_FechaInicio(datos.substring(datos.indexOf("<FechaInicio>")+22,datos.indexOf("</FechaInicio>")-3));
			actividad.set_FechaFin(datos.substring(datos.indexOf("<FechaFin>")+19,datos.indexOf("</FechaFin>")-3));
			actividad.set_Facebook(datos.substring(datos.indexOf("<Facebook>")+19,datos.indexOf("</Facebook>")-3));
			if(datos.indexOf("<Hora>")+6<datos.indexOf("</Hora>")-1)
				actividad.set_Hora(datos.substring(datos.indexOf("<Hora>")+6,datos.indexOf("</Hora>")-1));
			if(datos.indexOf("<Minutos>")+9<datos.indexOf("</Minutos>")-1)
			actividad.set_Minutos(datos.substring(datos.indexOf("<Minutos>")+9,datos.indexOf("</Minutos>")-1));
			
			
			
		    _actividades.add(actividad);
		    datos=	datos.substring(datos.indexOf("</Item>")+7);
		}
	}
	
	public ArrayList <regActividad> getArray()
	{
	   return _actividades;	   
		
	}
}
