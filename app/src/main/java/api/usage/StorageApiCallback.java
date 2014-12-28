package api.usage;


import android.util.Log;

import java.io.File;
import java.io.FileInputStream;

import api.files.FileMetadata;
import api.storages.Storage;

public class StorageApiCallback {

    public void oauth2(String storageName, String accessToken) {

    }

    public void getMetadata(FileMetadata metadata) {

    }

    public void getFile(String storageName, String webPath, String filePath) {
        Log.d("____path", filePath);
        StringBuilder b = new StringBuilder();

        try {
            FileInputStream stream = new FileInputStream(filePath);

            byte buffer[] = new byte[1024];
            int read;
            while((read = stream.read(buffer)) != -1) {
                b.append(new String(buffer, 0, read));
            }

            Log.d("____d", b.toString());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putFile(String storageName, String webPath, String filePath) {

    }
}
