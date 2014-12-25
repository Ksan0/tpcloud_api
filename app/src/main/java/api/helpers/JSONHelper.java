package api.helpers;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import api.files.FileMetadata;
import api.storages.Storage;


// it isn't copy-paste in code
// because clouds can use different architectures


public class JSONHelper {

    public static String parseGetFileResponseYandex(String str) {
        try {
            JSONObject json = new JSONObject(str);
            return json.getString("href");
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static FileMetadata parseMetadataDropbox(String str) {
        try {
            JSONObject json = new JSONObject(str);
            FileMetadata file = new FileMetadata();

            fillFileMetadataDropbox(file, json);

            if (json.has("contents")) {
                JSONArray contentsArray = json.getJSONArray("contents");
                for (int i = 0; i < contentsArray.length(); i++) {
                    JSONObject contentsJson = contentsArray.getJSONObject(i);
                    FileMetadata contentsFile = new FileMetadata();
                    fillFileMetadataDropbox(contentsFile, contentsJson);
                    file.addContainFile(contentsFile);
                }
            }

            return file;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static FileMetadata parseMetadataYandex(String str) {
        try {
            JSONObject json = new JSONObject(str);
            FileMetadata file = new FileMetadata();

            fillFileMetadataYandex(file, json);

            if (json.has("_embedded")) {
                JSONObject embedded = json.getJSONObject("_embedded");

                JSONArray contentsArray = embedded.getJSONArray("items");
                for (int i = 0; i < contentsArray.length(); i++) {
                    JSONObject contentsJson = contentsArray.getJSONObject(i);
                    FileMetadata contentsFile = new FileMetadata();
                    fillFileMetadataYandex(contentsFile, contentsJson);
                    file.addContainFile(contentsFile);
                }
            }

            return file;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void fillFileMetadataDropbox(FileMetadata file, JSONObject json)
            throws JSONException
    {
        file.setStorageName(Storage.STORAGE_DROPBOX);

        file.setStoragePath(json.getString("path"));

        if (json.has("bytes")) {
            file.setSize(json.getLong("bytes"));
        }

        file.setDir(json.getBoolean("is_dir"));

        if (json.has("mime_type")) {
            file.setMimeType(json.getString("mime_type"));
        }

        if (json.has("modified")) {
            file.setLastModified(json.getString("modified"));
        }
    }


    private static void fillFileMetadataYandex(FileMetadata file, JSONObject json)
            throws JSONException
    {
        file.setStorageName(Storage.STORAGE_YANDEX);

        file.setStoragePath(json.getString("path"));

        if (json.has("size")) {
            file.setSize(json.getLong("size"));
        }

        file.setDir(json.getString("type").equals("dir"));

        if (json.has("mime_type")) {
            file.setMimeType(json.getString("mime_type"));
        }

        if (json.has("modified")) {
            file.setLastModified(json.getString("modified"));
        }
    }
}
