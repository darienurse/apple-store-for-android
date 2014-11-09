package com.example.rssreader;

import android.os.Build;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

public class JSONManager {

    private static final int CONNECTION_TIMEOUT = 1000000;
    private static final int DATARETRIEVAL_TIMEOUT = 1000000;


    public static JSONObject requestWebService(String serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            String result = getResponseText(in);
            /*int maxLogSize = 1000;
            for(int i = 0; i <= result.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > result.length() ? result.length() : end;
                Log.v("TEST", result.substring(start, end));
            }*/
            return new JSONObject(result);

        } catch (MalformedURLException e) {
            // URL is invalid
            Log.e("ERROR1", e.toString());
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
            Log.e("ERROR2", e.toString());
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
            Log.e("ERROR3", e.toString());
        } catch (JSONException e) {
            // response body is no valid JSON string
            Log.e("ERROR4", e.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

}
