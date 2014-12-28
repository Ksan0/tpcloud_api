package com.mycompany.ksan0.tpcloud;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.io.File;
import java.text.SimpleDateFormat;

import api.storages.Storage;
import api.usage.StorageApiFront;


public class MyActivity extends Activity {

    StorageApiFront storageApiFront;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        try {
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").parse("Wed, 26 Mar 2014 18:32:02 +0000");
        } catch(Exception e) {
            e.printStackTrace();
        }

        // THIS FOLDERS MUST BE ALIVE
        new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/tpcloud_api").mkdir();
        new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/tpcloud_api/" + Storage.create(Storage.STORAGE_DROPBOX).getHumanReadName()).mkdir();
        new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/tpcloud_api/" + Storage.create(Storage.STORAGE_YANDEX).getHumanReadName()).mkdir();

        webView = (WebView) findViewById(R.id.webView1);

        storageApiFront = new StorageApiFront(this);
        //storageApiFront.oauth2(Storage.STORAGE_YANDEX, webView);
        /*storageApiFront.getMetadata(
                Storage.STORAGE_DROPBOX,
                DEV_HELPER.TOKEN_DROPBOX,
                "/rating.sql"
        );*/

//        storageApiFront.getFile(Storage.STORAGE_DROPBOX, DEV_HELPER.TOKEN_DROPBOX, "/Projects/cimg.ru/Статья.doc");
//        storageApiFront.getFile(Storage.STORAGE_DROPBOX, DEV_HELPER.TOKEN_DROPBOX, "/microWorld/microWorld_redactor.cpp");

        storageApiFront.putFile(Storage.STORAGE_YANDEX, DEV_HELPER.TOKEN_YANDEX, "qqrt", Environment.getExternalStorageDirectory() + "/zKgfq/spektr.fb2");
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
