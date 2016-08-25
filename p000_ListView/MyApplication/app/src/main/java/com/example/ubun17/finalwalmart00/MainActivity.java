package com.example.ubun17.finalwalmart00;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    String walURL = "http://api.walmartlabs.com/v1/search?query=chair&format=json&apiKey=usu6xmuc5dha75jepwnp2ds9";
    String baseURL = "http://api.walmartlabs.com/v1/search?query=";

    TextView textView;
    Button buSearch;
    EditText searchTerm;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        WalMartSearchCall walMartSearchCall = new WalMartSearchCall();
//        walMartSearchCall.execute(walURL);

        searchTerm = (EditText) findViewById(R.id.searchEdit);
        buSearch = (Button) findViewById(R.id.buSearch);
//
        buSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = searchTerm.getText().toString();
                String apiURL = baseURL+searchString+"&format=json&apiKey=usu6xmuc5dha75jepwnp2ds9";

                WalMartSearchCall walMartSearchCall = new WalMartSearchCall();
                walMartSearchCall.execute(apiURL);

            }
       });

        
        Log.d("start", "asdfasdfasdfasdfasdfasdfasdfasf");

        textView = (TextView) findViewById(R.id.textView);

        String stTest = null;

//        while (stTest == null) {
//
//            try {
//                TimeUnit.MILLISECONDS.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            WalSearchSingleton walSearchSingleton = WalSearchSingleton.getInstance();
//
//            String stTestnew = walSearchSingleton.getWalMartSearch().getQuery();
//            stTest = stTestnew;
//        }

        Log.d("before singleton", "----------------------------");
        Log.d("WalSingleton","/////////////// "+ stTest) ;

        arrayList = new ArrayList<String>();
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(MainActivity.this
                , android.R.layout.simple_list_item_1,arrayList);
       mListView.setAdapter(mAdapter);


    }//End of onCreate

    class WalMartSearchCall extends AsyncTask<String, Void, Void> {

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
                        //arrayList = new ArrayList<String>();
                        for (int i = 0; i < listItem.size(); i ++) {
                            String string = listItem.get(i).getName().toString();
                            Log.d("ddsdsdsd", string);
                            arrayList.add(string);
                        }

                       // mAdapter.notifyDataSetChanged();

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                                textView.setText(res);
                            }
                        });

                    }
                }// End of onResponse

            });
            return null;
        }
    }
}

