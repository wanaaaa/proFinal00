package com.example.ubun17.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "asdfasf";
    ListView mlistView;
    ArrayAdapter mAdapter;
    ArrayList<String> twitArray = new ArrayList<String>();
    String searchURL;

    EditText searchTerm;
    Button buSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buSearch = (Button) findViewById(R.id.button);
        searchTerm = (EditText) findViewById(R.id.searchEdit);

        mlistView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(MainActivity.this
                , android.R.layout.simple_list_item_1, twitArray);
        mlistView.setAdapter(mAdapter);

        buSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String search = searchTerm.getText().toString();
                searchURL = "https://api.twitter.com/1.1/search/tweets.json?q="+search;
                Log.d("asdf", searchURL);
                doMyAuthentication();
            }
        });

    }

    public void doMyAuthentication() {
        String consumer_key = "O4sU0x3BtyfZoH1cjWU8HrM2G";
        String consumer_secret = "oO6qmxSvKXSGqG0NtkHY9IDUKVfaxM45rI9U0z776HbtDBJiTw";
        String encodedkey = consumer_key+":"+consumer_secret;

        String base64EncodedString = Base64.encodeToString(encodedkey.getBytes(), Base64.NO_WRAP);

        Log.i(TAG, base64EncodedString);

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .build();


        ////////////////////////////
        RequestBody requestBody1 = new FormBody.Builder().build();

        Request apiRequest2 = new Request.Builder()
                .addHeader("Authorization", "Bearer " +
                        "AAAAAAAAAAAAAAAAAAAAAPjFuAAAAAAAw4DhWE0PW1fC%2FNu9IqlACrmkceQ%3DAfrebWvQJeZg6ttJrEEMWod9Wa7qGSyTM05dsFzae39UE5W4ZW")
                //.url("https://api.twitter.com//1.1/statuses/user_timeline.json?count=100&screen_name=NYCMayorsOffice")
                //.url("https://api.twitter.com/1.1/search/tweets.json?q=clinton&count=1")
                //.post(requestBody1)
                .url(searchURL)
                .build();//
//
        client.newCall(apiRequest2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    //cry
                } else {
                    Log.d("search made","/////////////////////////" );
                    String stResponse = response.body().string();
                    Gson gson = new Gson();
                    TweetsSearch tweetsSearch = gson.fromJson(stResponse, TweetsSearch.class);
 //                   int maxLogSize = 1000;
//                    String veryLongString = stResponse;
//                    for(int i=0; i<= veryLongString.length()/maxLogSize; i++){
//                        int start = i * maxLogSize;
//                        int end = (i+1)*maxLogSize;
//                        end = end>veryLongString.length()?veryLongString.length():end;
//                        Log.v("Hey", veryLongString.substring(start,end));
//                    }

                  Log.d("Hey",  tweetsSearch.getStatuses().get(0).getText());
                  Log.d("Hey//",  stResponse);


                for(int i = 0; i < tweetsSearch.getStatuses().size(); i ++) {
                    twitArray.add(tweetsSearch.getStatuses().get(i).getText());
                }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }

        });//End of new Call
        //////////////////////////////////////////////////////////

    }

}
