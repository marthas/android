package com.CulturaEnLasCalles;

public class RegPosicion {

	private String _Latitud = null;
	private String _Longitud = null;	
	private String _Domicilio = null;
	private String _Barrio = null;
	private String _Lugar = null;
	
	void setLatitud(String latitud)
	{
		_Latitud = latitud;
	}
	void setLongitud(String longitud)
	{
		_Longitud = longitud;
	}
	void setDomicilio(String domicilio)
	{
		_Domicilio = domicilio;
	}
	void setBarrio(String barrio)
	{
		_Barrio = barrio;
	}
	void setLugar(String lugar)
	{
		_Lugar = lugar;
	}
	String getLatitud()
	{
		return _Latitud;
	}
	String getLongitud()
	{
		return _Longitud;
	}
	String getDomicilio()
	{
		return _Domicilio;
	}
	String getBarrio()
	{
		return _Barrio;
	}
	String getLugar()
	{
		return _Lugar;
	}
}
