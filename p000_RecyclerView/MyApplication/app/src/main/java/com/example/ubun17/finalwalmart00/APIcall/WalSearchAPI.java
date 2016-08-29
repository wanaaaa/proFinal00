package com.example.ubun17.finalwalmart00.APIcall;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ubun17.finalwalmart00.WalMartSearch.Item;
import com.example.ubun17.finalwalmart00.WalMartSearch.WalMartSearch;
import com.example.ubun17.finalwalmart00.WalMartSearch.WalSearchSingleton;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ubun17 on 8/25/16.
 */
public class WalSearchAPI  extends AsyncTask<String, Void, Void> {

    ArrayList<String> walArray = new ArrayList<String>();

    public WalSearchAPI(ArrayList arr){
        walArray = arr;
    }

    @Override
    protected Void doInBackground(String... params) {

        String searchURL = params[0];
        OkHttpClient client = new OkHttpClient();

        final Request apiRequest = new Request.Builder().url(searchURL).build();

        client.newCall(apiRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("Sorry/", "connection Failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.i("connection", " failure");
                } else {
                    String stBody = response.body().string();
                    Gson gson = new Gson();
                    WalMartSearch walMartSearch = gson.fromJson(stBody, WalMartSearch.class);

                    WalSearchSingleton walSearchSingleton = WalSearchSingleton.getInstance();
                    walSearchSingleton.setWalSingleton(walMartSearch);
                    String ttest = walSearchSingleton.getWalMartSearch().getQuery();
                    Log.d("calling", "!!!!!!!!!!"+ ttest);

                    final String res = walMartSearch.getQuery();
                    String itemID =  walMartSearch.getItems().get(0).getItemId().toString();
                    Log.d("id", "++++++++++++++++++++++ "+ itemID);

                    List<Item> listItem = walSearchSingleton.getWalMartSearch().getItems();
//                    walArray = new ArrayList<String>();
                    for (int i = 0; i < listItem.size(); i ++) {
                        String string = listItem.get(i).getName().toString();
                        Log.d("ddsdsdsd", string);
                        walArray.add(string);
                    }

//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mAdapter.notifyDataSetChanged();
//                            textView.setText(res);
//                        }
//                    });

                }
            }// End of onResponse

        });
        return null;
    }
}

