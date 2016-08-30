package com.example.ubun17.ebay;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ubun17 on 8/26/16.
 */
public class APIWalMart {
    public APIWalMart() {
    }

    public String APIresponseST() {
        String ebayURL = "http://api.walmartlabs.com/v1/search?query=chair&format=json&apiKey=usu6xmuc5dha75jepwnp2ds9";

        OkHttpClient ebayClient = new OkHttpClient();

        final Request ebayRequest = new Request.Builder().url(ebayURL).build();

        ebayClient.newCall(ebayRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("connection", " failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseBody = response.body().string();

                Log.d("Wal Mart response", "//////////////// "+responseBody);
            }
        });


        return null;
    }
}
