package com.txtriet.rss.reader;

public class RSSItem {
	private String _title = null;
	private String _description = null;
	private String _category = null;
	private String _link = null;
	private String _pubdate = null;
	
	public String getTitle() {
		return _title;
	}
	public void setTitle(String _title) {
		this._title = _title;
	}
	public String getDescription() {
		return _description;
	}
	public void setDescription(String _description) {
		this._description = _description;
	}
	public String getCategory() {
		return _category;
	}
	public void setCategory(String _category) {
		this._category = _category;
	}
	public String getLink() {
		return _link;
	}
	public void setLink(String _link) {
		this._link = _link;
	}
	public String getPubDate() {
		return _pubdate;
	}
	public void setPubDate(String _pubdate) {
		this._pubdate = _pubdate;
	}
	
	// public String toString() {
	// // limit how much text you display
	// if (_title.length() > 42) {
	// return _title.substring(0, 42) + "...";
	// }
	// return _title;
	// }

}
