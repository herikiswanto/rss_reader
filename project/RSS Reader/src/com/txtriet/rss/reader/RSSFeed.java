package com.txtriet.rss.reader;

import java.util.List;
import java.util.Vector;

public class RSSFeed {
	private String _title = null;
	private String _pubdate = null;
	private int _itemcount = 0;
	private List<RSSItem> _itemlist;

	public List<RSSItem> getAllItems() {
		return _itemlist;
	}
	
	RSSFeed()
	{
		_itemlist = new Vector<RSSItem>(0);
	}
	
	int addItem(RSSItem item)
	{
		_itemlist.add(item);
		_itemcount++;
		return _itemcount;
	}
	
	RSSItem getItem(int location)
	{
		return _itemlist.get(location);
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String _title) {
		this._title = _title;
	}

	public String getPubDate() {
		return _pubdate;
	}

	public void setPubDate(String _pubdate) {
		this._pubdate = _pubdate;
	}

	public int getItemcount() {
		return _itemcount;
	}

	public void setItemcount(int _itemcount) {
		this._itemcount = _itemcount;
	}

}
