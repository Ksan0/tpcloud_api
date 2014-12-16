package api.storages;


import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

import api.files.FileMetadata;
import api.helpers.HTTPHelper;
import api.helpers.JSONHelper;

public class StorageYandex extends Storage {
    
    private static final String APP_KEY = "6d949608745a427ea749b1570a674b1d";
    private static final String AUTH_REDIRECT_URL= "tpyacloud://localhost";
    private static final String AUTH_URL = String.format(
            "https://oauth.yandex.com/authorize?" +
            "response_type=token&" +
            "client_id=%s",

            APP_KEY
    );
    private static final String METADATA_URL  = "https://cloud-api.yandex.net/v1/disk/resources?" +
                                                "path=%s&" +
                                                "limit=10000";
    
    @Override
    public String getAuthUrl() {
        return AUTH_URL;
    }

    @Override
    public String getAuthRedirectUrl() {
        return AUTH_REDIRECT_URL;
    }

    @Override
    public FileMetadata getMetadata(String accessToken, String path) {
        try {
            URL url = new URL(String.format(METADATA_URL, path));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", String.format("OAuth %s", accessToken));

            String response = HTTPHelper.makeRequest(connection);
            return JSONHelper.parseMetadataYandex(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
