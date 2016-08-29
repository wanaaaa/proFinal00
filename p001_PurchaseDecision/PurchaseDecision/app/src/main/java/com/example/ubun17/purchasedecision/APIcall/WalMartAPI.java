package com.example.ubun17.purchasedecision.APIcall;

import android.util.Log;

import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.SingleWarSearch;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.WalMartSearch;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ubun17 on 8/26/16.
 */
public class WalMartAPI {

    public WalMartAPI () {}

    public SingleWarSearch WalMartCall() {
        String WalMartURL = "http://api.walmartlabs.com/v1/search?query=chair&format=json&apiKey=usu6xmuc5dha75jepwnp2ds9";
        OkHttpClient walMartClient = new OkHttpClient();

        final Request WalMartRequest = new Request.Builder().url(WalMartURL).build();

        walMartClient.newCall(WalMartRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("connection", " failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseBody = response.body().string();
                Gson gson = new Gson();
                WalMartSearch walMartSearch = gson.fromJson(responseBody, WalMartSearch.class);
                SingleWarSearch searResult = SingleWarSearch.getInstance();
                //searResult.setWalSingleton(walMartSearch.getQuery().toString());
                searResult.setWalSingleton(walMartSearch.getQuery());
                Log.d("Wal in Class", searResult.getQuery());
            }
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SingleWarSearch searResult = SingleWarSearch.getInstance();
      //  searResult.setWalSingleton(searResult.getQuery());
        Log.d("before sigletn return", "sssssssssssssssssssss");
       //Log.d("walSearchResul", searResult.getQuery());
        return searResult;
    }
}