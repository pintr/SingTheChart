package com.mbrigadoi.singthechart.model.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsRequests {
    public static String getString(final String url) {
        String result;
        try {
            result = new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        StringBuilder builder = new StringBuilder();
                        URL mUrl = new URL(url);
                        HttpsURLConnection conn = (HttpsURLConnection) mUrl.openConnection();
                        conn.setRequestMethod("GET");
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        String line;
                        while ((line = br.readLine()) != null) {
                            builder.append(line);
                        }
                        br.close();

                        return builder.toString();
                    } catch (Exception e) {
                        Debug.logStackTrace(e);
                        return null;
                    }
                }
            }.execute().get();
        } catch (Exception e) {
            Debug.logStackTrace(e);
            result = null;
        }

        return result;
    }

    public static Bitmap getBitmap(final String url) {
        Bitmap result;
        try {
            result = new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... voids) {
                    try {
                        URL mUrl = new URL(url);
                        HttpsURLConnection conn = (HttpsURLConnection) mUrl.openConnection();
                        conn.setRequestMethod("GET");
                        InputStream is = conn.getInputStream();

                        return BitmapFactory.decodeStream(is);
                    } catch (Exception e) {
                        Debug.logStackTrace(e);
                        return null;
                    }
                }
            }.execute().get();
        } catch (Exception e) {
            Debug.logStackTrace(e);
            result = null;
        }

        return result;
    }


}
