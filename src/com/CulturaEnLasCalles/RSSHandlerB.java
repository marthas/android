package com.CulturaEnLasCalles;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class RSSHandlerB extends DefaultHandler{
	RSSFeedB _feed;
	RSSItemB _item;
	String _lastElementName = "";
	boolean bFoundChannel = false;
	final int RSS_BARRIO = 1;
	
	int depth = 0;
	int currentstate = 0;
	/*
	 * Constructor 
	 */
	RSSHandlerB()
	{
	}
	
	/*
	 * getFeed - this returns our feed when all of the parsing is complete
	 */
	RSSFeedB getFeed()
	{
		return _feed;
	}
	
	
	public void startDocument() throws SAXException
	{
		// initialize our RSSFeed object - this will hold our parsed contents
		_feed = new RSSFeedB();
		// initialize the RSSItem object - we will use this as a crutch to grab the info from the channel
		// because the channel and items have very similar entries..
		_item = new RSSItemB();

	}
	public void endDocument() throws SAXException
	{
	}
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException
	{
		depth++;
		if (localName.equals("channel"))
		{
			currentstate = 0;
			return;
		}
		if (localName.equals("descripcion"))
		{
			// record our feed data - we temporarily stored it in the item :)
			_feed.setBarrio(_item.getBarrio());			
		}
		if (localName.equals("item"))
		{
			// create a new item
			_item = new RSSItemB();
			return;
		}
		if (localName.equals("barrio"))
		{
			currentstate = RSS_BARRIO;
			return;
		}
				// if we don't explicitly handle the element, make sure we don't wind up erroneously 
		// storing a newline or other bogus data into one of our existing elements
		currentstate = 0;
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		depth--;
		if (localName.equals("item"))
		{
			// add our item to the list!
			_feed.addItem(_item);
			return;
		}
	}
	 
	public void characters(char ch[], int start, int length)
	{
		String theString = new String(ch,start,length);
		Log.i("RSSReader","characters[" + theString + "]");
		
		switch (currentstate)
		{
			case RSS_BARRIO:
				_item.setBarrio(theString);
				currentstate = 0;
				break;
					default:
				return;
		}
		
	}
}
