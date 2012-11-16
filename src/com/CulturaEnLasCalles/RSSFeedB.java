package com.CulturaEnLasCalles;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class RSSFeedB {
	private String _barrio = null;	
	private int _itemcount = 0;
	private ArrayList<RSSItemB> _itemlist;
	
	
	RSSFeedB()
	{
		_itemlist = new ArrayList(); 
	}
	int addItem(RSSItemB item)
	{
		_itemlist.add(item);
		_itemcount++;
		return _itemcount;
	}
	RSSItemB getItem(int location)
	{
		return _itemlist.get(location);
	}
	List getAllItems()
	{
		return _itemlist;
	}
	int getItemCount()
	{
		return _itemcount;
	}
	void setBarrio(String barrio)
	{
		_barrio = barrio;
	}
	
	
	RSSFeedB getFeed(String urlToRssFeed)
    {
    	try
    	{
    		// setup the url
    	   URL url = new URL(urlToRssFeed);
    	  
           // create the factory
           SAXParserFactory factory = SAXParserFactory.newInstance();
           // create a parser
           SAXParser parser = factory.newSAXParser();

           // create the reader (scanner)
           XMLReader xmlreader = parser.getXMLReader();
           // instantiate our handler
           RSSHandlerB theRssHandler = new RSSHandlerB();
           // assign our handler
           xmlreader.setContentHandler(theRssHandler);
           // get our data via the url class
           InputSource is = new InputSource(url.openStream());
           // perform the synchronous parse           
          xmlreader.parse(is);
           // get the results - should be a fully populated RSSFeed instance, or null on error
           return theRssHandler.getFeed();
         
    	}
    	catch (Exception ee)
    	{
    		// if we have a problem, simply return null	
    		return null;
    	}
    }
	
}
