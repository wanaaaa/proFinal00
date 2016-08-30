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
public class APIcall {
    String responseSt;

    public APIcall() {

    }

    public String APIresponseST() {
        String ebayURL = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=WanLim-priceCom-PRD-59a67cc35-c54e5d44&GLOBAL-ID=EBAY-US&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&keywords=harry%20potter&paginationInput.entriesPerPage=3";

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

                Log.d("Ebay response", "//////////////// "+responseBody);
            }
        });


        return null;
    }

}
