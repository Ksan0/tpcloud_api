package api.core;


import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;

import api.files.FileMetadata;
import api.helpers.HTTPHelper;
import api.storages.Storage;

public class StorageApiBack {

    public Object[] getMetadata(String storageName, String accessToken, String path) {
        Storage storage = Storage.create(storageName);

        FileMetadata metadata = storage.getMetadata(accessToken, path);

        return null;
    }

}
