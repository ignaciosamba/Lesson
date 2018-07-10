package com.example.isambataro.lesson2.api;

/**
 * Created by isambataro on 18/06/18.
 */

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String SWAPI_BASE_URL =
            "https://swapi.co/api";

    static HttpURLConnection mUrlConnection;

//    final static String PARAM_QUERY = "q";

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
//    final static String PARAM_SORT = "sort";
//    final static String sortBy = "stars";

    /**
     * Builds the URL used to query GitHub.
     *
     * @param searchQuery The keyword that will be queried for.
     * @return The URL to use to query the Swapi.
     */
    public static URL buildUrl(String searchQuery) {
        Uri builtUri = Uri.parse(SWAPI_BASE_URL).buildUpon()
                .appendPath(searchQuery)
                .appendPath("")
                .build();
        System.out.println("SAMBA bultUri: " + builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


public static String getResponseFromHttpUrl(URL url) throws IOException{
    StringBuilder result = new StringBuilder();
    try {
        mUrlConnection = (HttpURLConnection) url.openConnection();
        mUrlConnection.setInstanceFollowRedirects(false);
        if (mUrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream in = new BufferedInputStream(mUrlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
    }catch( Exception e) {
        e.printStackTrace();
    }
    finally {
        //SIEMPRE EL FINALLY ENTONCES SIEMPRE SE HACE EL DISCONECT.
        if (mUrlConnection != null) {
            mUrlConnection.disconnect();
        }
    }

    String resultado = result.toString();
    System.out.println("SAMBA RESULT: " + resultado);
    return resultado;
}

}
