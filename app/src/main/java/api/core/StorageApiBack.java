package api.core;


import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;

import api.files.FileMetadata;
import api.helpers.AndroidHelper;
import api.helpers.HTTPHelper;
import api.storages.Storage;

public class StorageApiBack {

    public Object[] getMetadata(String storageName, String accessToken, String path) {
        Storage storage = Storage.create(storageName);

        FileMetadata metadata = storage.getMetadata(accessToken, path);

        return new Object[] {metadata};
    }

    public Object[] getFile(String storageName, String accessToken, String path, String dirName) {
        Storage storage = Storage.create(storageName);
        String fileKey = AndroidHelper.filePathToKey(storageName, path);

        File file = new File(dirName, fileKey);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            storage.getFile(accessToken, path, fileOutputStream);
            fileOutputStream.close();
            return new Object[] {storageName, path, dirName, fileKey};
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[] {null, null, null, null};
        }
    }

}
