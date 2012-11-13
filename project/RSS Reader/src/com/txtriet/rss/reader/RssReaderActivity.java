package com.txtriet.rss.reader;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RssReaderActivity extends ListActivity {
	public static final String RSS_LINK = "rss_link";

	final String labels[] = new String[] { "Trang chủ", "Xã hội", "Thế giới",
			"Kinh doanh", "Văn hoá", "Thể thao", "Pháp luật", "Đời sống",
			"Khoa học", "Vi tính", "Ôtô - Xe máy", "Bạn đọc viết", "Tâm sự",
			"Cười" };
	final String rss[] = new String[] {
			"http://vnexpress.net/rss/gl/trang-chu.rss",
			"http://vnexpress.net/rss/gl/xa-hoi.rss",
			"http://vnexpress.net/rss/gl/the-gioi.rss",
			"http://vnexpress.net/rss/gl/kinh-doanh.rss",
			"http://vnexpress.net/rss/gl/van-hoa.rss",
			"http://vnexpress.net/rss/gl/the-thao.rss",
			"http://vnexpress.net/rss/gl/phap-luat.rss",
			"http://vnexpress.net/rss/gl/doi-song.rss",
			"http://vnexpress.net/rss/gl/khoa-hoc.rss",
			"http://vnexpress.net/rss/gl/vi-tinh.rss",
			"http://vnexpress.net/rss/gl/oto-xe-may.rss",
			"http://vnexpress.net/rss/gl/ban-doc-viet.rss",
			"http://vnexpress.net/rss/gl/ban-doc-viet-tam-su.rss",
			"http://vnexpress.net/rss/gl/cuoi.rss" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_reader);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, labels));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_rss_reader, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(RssReaderActivity.this, RssItemListActivity.class);
		i.putExtra(RSS_LINK, rss[position]);
		startActivity(i);
	}
}
