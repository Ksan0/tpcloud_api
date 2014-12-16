package com.mycompany.ksan0.tpcloud;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import api.storages.Storage;
import api.usage.StorageApiFront;


public class MyActivity extends Activity {

    StorageApiFront storageApiFront;
    WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        webView = (WebView) findViewById(R.id.webView1);

        storageApiFront = new StorageApiFront(this);
        storageApiFront.oauth2(Storage.STORAGE_YANDEX, webView);
        /*storageApiFront.getMetadata(
                Storage.STORAGE_DROPBOX,
                "",
                ""
        );*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
