package api.helpers;


import android.util.Log;

import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

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


    public static void makeRequest(HttpURLConnection connection, OutputStream resultStream) {

        try {
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            byte buffer[] = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                resultStream.write(buffer, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

    }


    public static void makeRequest(HttpURLConnection connection, InputStream dataStream) {

        try {
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();
            byte buffer[] = new byte[1024];
            int read;
            while ((read = dataStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

    }

}
