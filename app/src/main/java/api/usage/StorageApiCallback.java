package api.usage;


import android.util.Log;

import api.storages.Storage;

public class StorageApiCallback {

    public void oauth2(Storage storage, String accessToken) {
        Log.d("___acc", accessToken);
    }

    public void getMetadata() {

    }

    public void Foo(Integer x, String y, String z) {
        Log.d("___Callback", String.format("x=%s, y=%s, z=%s", x.toString(), y, z));
    }

}
