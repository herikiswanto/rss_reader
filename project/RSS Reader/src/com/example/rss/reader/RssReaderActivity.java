package com.example.rss.reader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RssReaderActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_reader);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_rss_reader, menu);
        return true;
    }
}
