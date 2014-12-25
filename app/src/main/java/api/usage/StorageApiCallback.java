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

    public void getFile(String storageName, String path, String dirName, String fileKey) {
        Log.d("____key", fileKey);
        StringBuilder b = new StringBuilder();

        try {
            FileInputStream stream = new FileInputStream(dirName + "/" + fileKey);

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

}
