package api.core;


import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

import api.files.FileMetadata;
import api.storages.Storage;

public class StorageApiBack {

    public Object[] getMetadata(String storageName, String accessToken, String path) {
        Storage storage = Storage.create(storageName);

        FileMetadata metadata = storage.getMetadata(accessToken, path);

        return new Object[] {metadata};
    }

    public Object[] getFile(String storageName, String accessToken, String path) {
        Storage storage = Storage.create(storageName);

        String pathElems[] = path.split(File.separator);
        String currentPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Storage.EXTERNAL_STORAGE_NAME + "/" + storage.getHumanReadName();

        for (int i = 0; i < pathElems.length; i++) {
            if (pathElems[i].isEmpty())
                continue;

            currentPath += "/" + pathElems[i];
            File f = new File(currentPath);
            if (i < pathElems.length - 1 && !f.isDirectory()) {
                if (!f.mkdir()) {
                    Log.e("tpcloud_api", "cannot create dir " + f.getAbsolutePath());
                }
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(currentPath);
            storage.getFile(accessToken, path, fileOutputStream);
            fileOutputStream.close();
            return new Object[] {storageName, path, currentPath};
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[] {null, null, null};
        }
    }

}
