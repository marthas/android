package com.CulturaEnLasCalles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class LeerUrl {
	
	private String _URL=null;
	
		
	LeerUrl(String url)
	{
		_URL=url;
	}
	public InputStream Leer()
	{
		
		HttpURLConnection con = null;

		InputStream is=null;
		try {
			URL url = new URL(_URL);
			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(10000 /* milliseconds */);
			con.setConnectTimeout(15000 /* milliseconds */);
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.addRequestProperty("Referer", "http://blog.dahanne.net");
			// Start the query
			con.connect();
			is = con.getInputStream();
		}catch (IOException e) {
                        //handle the exception !
			e.printStackTrace();
		}
		return is;
 		
	}
		public String datos() throws Exception
		{
			InputStream _sitio=Leer();
			 
			 BufferedReader reader = new BufferedReader(new InputStreamReader(_sitio));
			    StringBuilder sb = new StringBuilder();
			    String line = null;
			    int c=0;
			    boolean llego = false;
			    while ((line = reader.readLine()) != null) {
			    	
			    		sb.append(line);
			    				    	
			    }

			    _sitio.close();

			     
			  return sb.toString();
			
			
		}
	 public String parser() throws Exception
	{
		 InputStream _sitio=Leer();
		 
		 BufferedReader reader = new BufferedReader(new InputStreamReader(_sitio));
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    int c=0;
		    boolean llego = false;
		    while ((line = reader.readLine()) != null&& !llego) {
		    	if (line.indexOf("$(document).ready(function()")!=-1)
		    	{	
		    		sb.append(line);
		    		c=1;
		    	}
		    	if (c>0 && c<5)
		    	{
		    		sb.append(line);
		    		c++;
		    	}
		    	if (c==5)
		    		llego = true;
		    	
		    }

		    _sitio.close();

		     
		 if (sb.toString()!=null)
	
			 return  sb.toString().substring((sb.toString().indexOf("$(document).ready(function()")+1),sb.toString().indexOf("BusquedaPage.map.setCenter(myCord)"));
		 else
			 return null;
		 
	}
	 
}
