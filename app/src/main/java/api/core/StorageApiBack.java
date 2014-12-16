package api.core;


import android.util.Log;

import api.helpers.HTTPHelper;
import api.storages.Storage;

public class StorageApiBack {

    public Object[] getMetadata(String storageName, String accessToken, String path) {
        Storage storage = Storage.create(storageName);

        String url = storage.getMetadataUrl(accessToken, path);
        String response = HTTPHelper.makeRequest(url, "GET");

        Log.d("___", response);
        return null;
    }

    public Object[] Foo(String x, String y, Integer z) {

        Log.d("Foo.x", x);
        Log.d("Foo.y", y);
        Log.d("Foo.z", z.toString());

        return new Object[]{z, x, y};
    }

}
