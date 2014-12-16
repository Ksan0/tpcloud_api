package api.helpers;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {

    public static String makeRequest(String url, String method) {
        HttpURLConnection connection = null;

        try {
            StringBuilder stringBuilder = new StringBuilder();

            URL requestURL = new URL(url);
            connection = (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod(method);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            byte buffer[] = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, read));
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
