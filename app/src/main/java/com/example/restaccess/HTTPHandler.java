package com.example.restaccess;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HTTPHandler {
    public HTTPHandler() {

    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public String getAccess(String url) {
        String response = null;
        URL u = null;

        try {
            u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");

            InputStream i = new BufferedInputStream(conn.getInputStream());
//            BufferedReader in = new BufferedReader(new InputStreamReader(i));
//            StringBuffer r = new StringBuffer();
//            while ((response=in.readLine())!=null) {
//                r.append(response);
//            }
//            response = r.toString();
            response = converttostring(i);

//            InputStream i = new BufferedInputStream(conn.getInputStream());
//            response = new String(i.readAllBytes(), StandardCharsets.UTF_8);
            Log.d("REST01",response);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    public String postAccess(String url, ArrayList<String> key, ArrayList value) {
        String response = null;
        URL u = null;
        try {
            u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            JSONObject params = new JSONObject();
            for (int x=0;x<key.size();x++) {
                params.put(key.get(x),value.get(x));
            }
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            Log.d("REST01",params.toString());
            wr.write(params.toString());
            wr.flush();

            InputStream i = new BufferedInputStream(conn.getInputStream());
//            BufferedReader in = new BufferedReader(new InputStreamReader(i));
//            StringBuffer r = new StringBuffer();
//            while ((response=in.readLine())!=null) {
//                r.append(response);
//            }
//            response = r.toString();
            response = converttostring(i);
            Log.d("REST01",response);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    private String converttostring(InputStream i) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(i));
        StringBuilder sb = new StringBuilder();
        String dummy;

        while ((dummy=r.readLine())!=null) {
            sb.append(dummy).append("\n");
        }
        return sb.toString();
    }


}
