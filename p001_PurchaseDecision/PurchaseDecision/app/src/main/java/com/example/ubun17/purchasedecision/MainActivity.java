package com.example.ubun17.purchasedecision;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.Item;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.SingleWarSearch;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.WalMartSearch;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsycAPIcalling asycAPIcalling = new AsycAPIcalling();
        asycAPIcalling.execute("asdf");

//        WalMartAPI walMartAPI = new WalMartAPI();
//           walMartAPI.WalMartCall();

        SingleWarSearch searResult = SingleWarSearch.getInstance();

        String stQuery = searResult.getQuery();// "ccccccccccccccc";// searResult.getQuery();
        //searResult.setWalSingleton(searResult.getQuery());
        Log.d("onCreate", "sssssssssssssssssssss");
        Log.d("walSearchResul", stQuery);

    }

    class AsycAPIcalling extends AsyncTask<String, Double, List<Item>> {

        @Override
        protected List<Item> doInBackground(String... strings) {
//            WalMartAPI walMartAPI = new WalMartAPI();
//            walMartAPI.WalMartCall();


/////////////////////////////////////////////////////////////////////////
            String WalMartURL = "http://api.walmartlabs.com/v1/search?query=chair&format=json&apiKey=usu6xmuc5dha75jepwnp2ds9";
            OkHttpClient walMartClient = new OkHttpClient();

            final Request WalMartRequest = new Request.Builder().url(WalMartURL).build();

            final List<Item> items = new ArrayList<Item>();

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

                     //items = walMartSearch.getItems();
                }
            });


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SingleWarSearch searResult2 = SingleWarSearch.getInstance();

            String stQuery = searResult2.getQuery();
            // searResult.setWalSingleton(searResult.getQuery());
            Log.d("Afer sigletn return", "==================");
            if (stQuery == null) {
                Log.d("stQuery is" , "nulnulnulllllllllllllllllllllllllllll");
            } else {
                Log.d("walSearchResul", stQuery);
            }
           //


///////////////////////////////////////////////////////////////////////
            WalMartSearch walSer = new WalMartSearch();
            return walSer.getItems();
        }

        @Override
        protected void onPostExecute(List<Item> items) {
//            SingleWarSearch singleWarSearch = SingleWarSearch.getInstance();
//            String asdsss = singleWarSearch.getWalMartSearch().getQuery();
//
//            Log.d("Wal in onPostExecute", "//////////////// "+asdsss);
//            Log.d("Wal in onPostExecute", "//////////////// " + str);
//            Toast.makeText(MainActivity.this, str,Toast.LENGTH_LONG).show();
          String name = items.toString();
            //Log.d("onPostExecute","zzzzzzzzzzzzz"+ name);
        }

    }
}
