package com.example.ubun17.purchasedecision;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ubun17.purchasedecision.APIcall.EbayAPI;
import com.example.ubun17.purchasedecision.APIcall.WalMartAPI;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.Item;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.SingleWarSearch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    AdapterRecyItem adapter;
    ArrayList<Item> mItems;

    EditText inputItem;
    Button buItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buItem = (Button) findViewById(R.id.buSearch);
        buItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputItem = (EditText) findViewById(R.id.inputSearch);

                String stSearchItem = inputItem.getText().toString();
                AsycAPIcalling asycAPIcalling = new AsycAPIcalling();
                asycAPIcalling.execute(stSearchItem);
            }
        });//End of button

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleViewItemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mItems = new ArrayList<>();
        adapter = new AdapterRecyItem(mItems);
        mRecyclerView.setAdapter(adapter);
    }

    class AsycAPIcalling extends AsyncTask<String, Double, List<Item>> {

        @Override
        protected List<Item> doInBackground(String... strings) {
            WalMartAPI walCalling = new WalMartAPI(strings[0]);
            walCalling.WalMartCall();

            SingleWarSearch singleWarSearch = SingleWarSearch.getInstance();
            ArrayList<Item> walItems = singleWarSearch.getItemList();

            for (int i = 0; i < walItems.size(); i ++) {
                String searTerm = walItems.get(i).getName();
                EbayAPI ebayCall= new EbayAPI(searTerm);
                ebayCall.EbayCall();

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);

            Toast.makeText(MainActivity.this, "asdf", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            SingleWarSearch singleWarSearch = SingleWarSearch.getInstance();
            String strWal = singleWarSearch.getQuery();

            int testInt = singleWarSearch.getEbayExampleList().size();
;
            String timesEbay;
            for (int i = 0; i < testInt; i ++ ) {
                if (singleWarSearch.getEbayExampleList().get(i) != null) {
                    timesEbay = String.valueOf(singleWarSearch.getEbayExampleList()
                            .get(i).getFindItemsByKeywordsResponse().get(0).getTimestamp());
                } else {
                    timesEbay = "nulllllllllllllllll";
                }

                Log.d("Times", "@@@@@@"+ timesEbay);
            }



            String test = String.valueOf(testInt);
//            String testTwo = singleWarSearch.getEbayExampleList().get(0)
//                    .getFindItemsByKeywordsResponse().get(0).getSearchResult().get(0)
//                    .getItem().get(0).getSellingStatus().get(0).getCurrentPrice().get(0)
//                    .getValue();
//            Log.d("test", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ testTwo);

            String testOne = String.valueOf(singleWarSearch.getItemList().size());
            Log.d("test null", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ test);
            Log.d("test walsize", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ testOne);

            Log.d("Wal in onPostExecute", "//////////////// "+strWal);

            final ArrayList<Item> data = singleWarSearch.getItemList();

            mItems.clear();
            mItems.addAll(data);
            adapter.notifyDataSetChanged();
        }

    }
}
