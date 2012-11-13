package com.txtriet.rss.reader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RssItemListActivity extends Activity implements
		OnItemClickListener {
	public static final String FEED_LINK = "feed link";

	String rss;

	TextView feedtitle;
	TextView feedpubdate;
	ListView itemlist;
	RSSFeed feed;

	Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// do something with the message...
			// update GUI if needed!
			RSSItem item = (RSSItem) msg.obj;
			feed.addItem(item);
			UpdateDisplay();
		}// handleMessage
	};// myHandler

	Thread backgJob = new Thread(new Runnable() {
		public void run() {
			// ...do some busy work here ...
			// get a token to be added to
			// the main's message queue
			getFeed(rss);
		}// run
	});// Thread

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_item_list);

		feedtitle = (TextView) findViewById(R.id.feedtitle);
		feedpubdate = (TextView) findViewById(R.id.feedpupdate);
		itemlist = (ListView) findViewById(R.id.itemlist);

		rss = getIntent().getStringExtra(RssReaderActivity.RSS_LINK);

		feed = new RSSFeed();
		backgJob.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_rss_reader, menu);
		return true;
	}

	private RSSFeed getFeed(String urlToRssFeed) {
		try {
			// setup the url
			URL url = new URL(urlToRssFeed);
			// create the factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// create a parser
			SAXParser parser = factory.newSAXParser();
			// create the reader (scanner)
			XMLReader xmlreader = parser.getXMLReader();
			// instantiate our handler
			RSSHandler theRssHandler = new RSSHandler(myHandler);
			// assign our handler
			xmlreader.setContentHandler(theRssHandler);
			// get our data through the url class
			InputSource is = new InputSource(url.openStream());
			// perform the synchronous parse
			xmlreader.parse(is);
			// get the results - should be a fully populated RSSFeed instance,
			// or null on error
			return theRssHandler.getFeed();
		} catch (Exception e) {
			// if you have a problem, simply return null
			return null;
		}
	}

	private void UpdateDisplay() {

		if (feed == null) {
			feedtitle.setText("No RSS Feed Available");
			return;
		}
		feedtitle.setText(feed.getTitle());
		feedpubdate.setText(feed.getPubDate());
		ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(this,
				android.R.layout.simple_list_item_1, feed.getAllItems());
		itemlist.setAdapter(adapter);
		itemlist.setSelection(0);
		itemlist.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		// Uri uri = Uri.parse(feed.getItem(position).getLink());
		// Intent i = new Intent(Intent.ACTION_VIEW, uri);
		Intent i = new Intent(RssItemListActivity.this, RssItemActivity.class);
		i.putExtra(FEED_LINK, feed.getItem(position).getLink());
		startActivity(i);
	}

}
