package com.only.framework.library.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/8/30.
 * 图片地址转换Bitmap
 */

public class ApacheHttpClient {
    private static final String TAG = "Error";

    public InputStream httpGet(String url) {
        InputStream result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            if (httpStatus == HttpStatus.SC_OK) {
                InputStream in = httpResponse.getEntity().getContent();
                try {
                    result = in;
                } catch (Exception e) {
                    Log.i(TAG, "Exception");
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                result = null;
            }
        } catch (ClientProtocolException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
            Log.i(TAG, "ClientProtocolException");
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
            Log.i(TAG, "ClientProtocolException");
        }
        return result;
    }

    public Bitmap getHttpBmp(String url) {
        Bitmap bm = null;
        InputStream is = httpGet(url);
        bm = BitmapFactory.decodeStream(is);
        return bm;
    }
}
