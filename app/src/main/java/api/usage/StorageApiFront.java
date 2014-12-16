package api.usage;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import api.core.StorageApiBack;
import api.core.StorageApiIntentParams;
import api.core.StorageApiService;
import api.storages.Storage;


public class StorageApiFront {

    Activity activity;
    BroadcastReceiver broadcastReceiver;
    StorageApiCallback storageApiCallback;

    public StorageApiFront(Activity activity) {
        this.activity = activity;

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                StorageApiIntentParams params = intent.getParcelableExtra(StorageApiBack.class.getName());
                Object objectParams[] = params.getParams();

                Class classParams[] = new Class[objectParams.length];
                for (int i = 0; i < objectParams.length; i++) {
                    classParams[i] = objectParams[i].getClass();
                }

                Method method;
                try {
                    method = storageApiCallback.getClass().getMethod(params.getMethodName(), classParams);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    return;
                }

                try {
                    method.invoke(storageApiCallback, objectParams);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        activity.registerReceiver(broadcastReceiver, new IntentFilter(StorageApiBack.class.getCanonicalName()));

        storageApiCallback = new StorageApiCallback();
    }

    public void Shutdown() {
        activity.unregisterReceiver(broadcastReceiver);
    }

    private void startService(String methodName, Object objectParams[]) {
        StorageApiIntentParams params = new StorageApiIntentParams();
        params.setMethodName(methodName);
        params.setParams(objectParams);

        Intent intent = new Intent(activity, StorageApiService.class);
        intent.putExtra(StorageApiFront.class.getName(), params);

        activity.startService(intent);
    }


    // *** API METHODS BEGIN ***

    private Storage oauth2Storage;
    public void oauth2(String storageName, WebView webView) {
        oauth2Storage = Storage.create(storageName);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("___redir", oauth2Storage.getAuthRedirectUrl());
                int xxx = url.indexOf(oauth2Storage.getAuthRedirectUrl());
                Log.d("___redirI", Integer.toString(xxx));
                Log.d("___redirU", url);

                if (url.indexOf(oauth2Storage.getAuthRedirectUrl()) != 0) {
                    return false;  // let's load this url
                }

                HashMap<String, String> getParameters = new HashMap<String, String>();
                try {
                     List<NameValuePair> getParametersPairs = URLEncodedUtils.parse(new URI(url.replace('#', '?')), "UTF-8");
                    for (NameValuePair p: getParametersPairs) {
                        getParameters.put(p.getName(), p.getValue());
                    }
                } catch(Exception e) {
                    Log.e("SigningService", e.toString());
                    return false;
                }

                storageApiCallback.oauth2(oauth2Storage, getParameters.get("access_token"));

                return true;
            }
        });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(oauth2Storage.getAuthUrl());
    }


    public void getMetadata(String storageName, String accessToken, String path) {
        startService("getMetadata", new Object[] {storageName, accessToken, path});
    }


    public void Foo(String x, String y, int z) {
        startService("Foo", new Object[] {x, y, z});
    }


    // *** API METHODS END ***
}