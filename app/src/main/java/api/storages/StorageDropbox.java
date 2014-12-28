package api.storages;


import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import api.files.FileMetadata;
import api.helpers.HTTPHelper;
import api.helpers.JSONHelper;

public class StorageDropbox extends Storage {
    private static final String APP_KEY = "601z07rjcs1mydq";
    private static final String AUTH_REDIRECT_URL = "tpdbcloud://localhost";
    private static final String AUTH_URL = String.format(
            "https://www.dropbox.com/1/oauth2/authorize?" +
            "response_type=token&" +
            "client_id=%s&" +
            "redirect_uri=%s",

            APP_KEY,
            AUTH_REDIRECT_URL
    );
    private static final String METADATA_URL = "https://api.dropbox.com/1/metadata/auto/%s?access_token=%s";
    private static final String GET_FILE_URL = "https://api-content.dropbox.com/1/files/auto/%s?access_token=%s";
    private static final String PUT_FILE_URL = "https://api-content.dropbox.com/1/files_put/auto/%s?overwrite=true&access_token=%s";

    @Override
    public String getHumanReadName() {
        return "dropbox";
    }

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
            URL url = new URL(String.format(METADATA_URL, URLEncoder.encode(path, "UTF-8"), accessToken));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String response = HTTPHelper.makeRequest(connection);
            return JSONHelper.parseMetadataDropbox(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean getFile(String accessToken, String path, OutputStream outputStream) {
        try {
            URL url = new URL(String.format(GET_FILE_URL, URLEncoder.encode(path, "UTF-8"), accessToken));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            HTTPHelper.makeRequest(connection, outputStream);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean putFile(String accessToken, String path, InputStream stream, long streamLength) {
        try {
            URL url = new URL(String.format(PUT_FILE_URL, URLEncoder.encode(path, "UTF-8"), accessToken));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Length", Long.toString(streamLength));
            connection.setRequestProperty("Content-Type", "*/*");

            HTTPHelper.makeRequest(connection, stream);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
