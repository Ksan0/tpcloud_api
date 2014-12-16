package api.helpers;


import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {

    public static String makeRequest(HttpURLConnection connection) {

        try {
            connection.connect();
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = connection.getInputStream();
            byte buffer[] = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, read));
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.disconnect();
        }

    }



}
