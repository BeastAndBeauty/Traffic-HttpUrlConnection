package com.traffic.pyp.traffic3.Util;

import android.os.Handler;
import android.os.Looper;

import com.traffic.pyp.traffic3.Callback.OnResponseListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class NetUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void asyncRequest(final String url, final String requestBody, final OnResponseListener responseListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL murl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) murl.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setReadTimeout(6 * 1000);
                    connection.setConnectTimeout(6 * 1000);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    if (requestBody != null) {
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                        outputStreamWriter.write(requestBody);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseListener.onResponse(stringBuffer.toString());
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseListener.onResponse("");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseListener.onResponse("");
                        }
                    });
                }

            }
        }).start();

    }
}
