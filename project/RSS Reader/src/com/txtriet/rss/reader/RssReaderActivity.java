package com.txtriet.rss.reader;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class RssReaderActivity extends ListActivity {
	public static final String RSS_LINK = "rss_link";
	private RssDBAdapter dbHelper;
	private SimpleCursorAdapter dataAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_reader);
		
		dbHelper = new RssDBAdapter(this);
		dbHelper.open();

		displayListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_rss_reader, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the cursor, positioned to the corresponding row in the result set
		Cursor cursor = (Cursor) l.getItemAtPosition(position);

		String link = cursor.getString(cursor.getColumnIndexOrThrow("link"));

		Intent i = new Intent(RssReaderActivity.this, RssItemListActivity.class);
		i.putExtra(RSS_LINK, link);
		startActivity(i);
	}

	private void displayListView() {

		Cursor cursor = dbHelper.getAll();

		// The desired columns to be bound
		String[] columns = new String[] { RssDBAdapter.KEY_NAME};

		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.tvRssName};

		// create the adapter using the cursor pointing to the desired data
		// as well as the layout information
		dataAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.rss_link_row, cursor, columns, to);
		
		this.setListAdapter(dataAdapter);
		
	}
}
