package com.txtriet.rss.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RssItemActivity extends Activity {
	
	private WebView webView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_item);
		
		webView = (WebView)findViewById(R.id.webView1);
		
		Intent i = getIntent();
				
		String url = i.getStringExtra(RssItemListActivity.FEED_LINK);
		
		webView.loadUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_rss_reader, menu);
		return true;
	}
}
