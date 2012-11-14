package com.txtriet.rss.reader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class RssDBAdapter {
	public static final String KEY_ROWID = "recid";
	public static final String KEY_NAME = "name";
	public static final String KEY_LINK = "link";

	private static final String DATABASE_NAME = "rssDB2";
	private static final String SQLITE_TABLE = "tblRSS";
	private SQLiteDatabase db;

	private Context context;

	/**
	 * @param context
	 */
	public RssDBAdapter(Context context) {
		this.context = context;
	}

	/**
	 * Open database.
	 */
	public void open() {
		try {
			db = context.openOrCreateDatabase(DATABASE_NAME,
					Context.MODE_PRIVATE, null);
			//generate sample rss
			if(!isTableExists(SQLITE_TABLE)){
				db.execSQL("create table tblRSS ("
						+ " recID integer PRIMARY KEY autoincrement, "
						+ " name text, " + " link text ); ");
				generateSampleRss();
			}
		} catch (SQLiteException e) {
			// log error
		} catch (Exception e) {
			// log error
		}
	}

	/**
	 * Insert record.
	 * 
	 * @param name
	 * @param link
	 */
	public int insert(String name, String link) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_LINK, link);
		return (int) db.insert(SQLITE_TABLE, null, initialValues);
	}

	/**
	 * Delete record.
	 * 
	 * @param name
	 */
	public int delete(String name) {
		String[] whereArgs = new String[] { name };
		return db.delete(SQLITE_TABLE, "name = ?", whereArgs);
	}

	public Cursor getAll() {
		Cursor mCursor = db.query(SQLITE_TABLE, new String[] { "rowid _id", KEY_NAME, KEY_LINK}, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * Close database.
	 */
	public void close() {
		if (db != null) {
			db.close();
		}
	}
	
	/**
	 * Generate sample rss
	 */
	public void generateSampleRss(){
		insert("Trang chủ", "http://vnexpress.net/rss/gl/trang-chu.rss");
		insert("Xã hội", "http://vnexpress.net/rss/gl/xa-hoi.rss" );
		insert("Thế giới", "http://vnexpress.net/rss/gl/the-gioi.rss" );
		insert("Kinh doanh", "http://vnexpress.net/rss/gl/kinh-doanh.rss" );
		insert("Văn hoá", "http://vnexpress.net/rss/gl/van-hoa.rss" );
		insert("Thể thao", "http://vnexpress.net/rss/gl/the-thao.rss" );
		insert("Pháp luật", "http://vnexpress.net/rss/gl/phap-luat.rss" );
		insert("Đời sống", "http://vnexpress.net/rss/gl/doi-song.rss" );
		insert("Khoa học", "http://vnexpress.net/rss/gl/khoa-hoc.rss" );
		insert("Vi tính", "http://vnexpress.net/rss/gl/vi-tinh.rss" );
		insert("Ôtô - Xe máy", "http://vnexpress.net/rss/gl/oto-xe-may.rss" );
		insert("Bạn đọc viết", "http://vnexpress.net/rss/gl/ban-doc-viet.rss" );
		insert("Tâm sự", "http://vnexpress.net/rss/gl/tam-su.rss" );
		insert("Cười", "http://vnexpress.net/rss/gl/cuoi.rss" );
	}
	
	boolean isTableExists(String tableName)
	{
	    if (tableName == null || db == null || !db.isOpen())
	    {
	        return false;
	    }
	    Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
	    if (!cursor.moveToFirst())
	    {
	        return false;
	    }
	    int count = cursor.getInt(0);
	    cursor.close();
	    return count > 0;
	}
}
