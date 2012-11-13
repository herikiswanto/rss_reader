package com.txtriet.rss.reader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class RSSHandler extends DefaultHandler {
	RSSFeed _feed;
	RSSItem _item;
	String _lastElementName = "";
	boolean bFoundChannel = false;
	final int RSS_TITLE = 1;
	final int RSS_LINK = 2;
	final int RSS_DESCRIPTION = 3;
	final int RSS_CATEGORY = 4;
	final int RSS_PUBDATE = 5;

	int depth = 0;
	int currentstate = 0;
	Handler myHandler;

	RSSHandler(Handler handler) {
		this.myHandler = handler;
	}

	RSSFeed getFeed() {
		return _feed;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		_feed = new RSSFeed();
		_item = new RSSItem();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		depth++;
		if (localName.equals("channel")) {
			currentstate = 0;
			return;
		}
		if (localName.equals("image")) {
			_feed.setTitle(_item.getTitle());
			_feed.setPubDate(_item.getPubDate());
		}
		if (localName.equals("item")) {
			_item = new RSSItem();
			return;
		}
		if (localName.equals("title")) {
			currentstate = RSS_TITLE;
			return;
		}
		if (localName.equals("description")) {
			currentstate = RSS_DESCRIPTION;
			return;
		}
		if (localName.equals("link")) {
			currentstate = RSS_LINK;
			return;
		}
		if (localName.equals("category")) {
			currentstate = RSS_CATEGORY;
			return;
		}
		if (localName.equals("pubDate")) {
			currentstate = RSS_PUBDATE;
			return;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		depth--;
		if (localName.equals("item")) {
			_feed.addItem(_item);
			Message msg = myHandler.obtainMessage(1, (RSSItem) _item);
			// deliver message to the
			// main's message-queue
			myHandler.sendMessage(msg);
			return;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String theString = new String(ch, start, length);
		Log.i("RSSReader", "characters[" + theString + "]");
		switch (currentstate) {
		case RSS_TITLE:
			_item.setTitle(theString);
			currentstate = 0;
			break;
		case RSS_LINK:
			_item.setLink(theString);
			currentstate = 0;
			break;
		case RSS_DESCRIPTION:
			_item.setDescription(theString);
			currentstate = 0;
			break;
		case RSS_CATEGORY:
			_item.setCategory(theString);
			currentstate = 0;
			break;
		case RSS_PUBDATE:
			_item.setPubDate(theString);
			currentstate = 0;
			break;
		default:
			return;
		}
	}

}
