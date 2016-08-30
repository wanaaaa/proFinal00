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
import android.widget.ImageView;

import com.example.ubun17.purchasedecision.APIcall.WalMartAPI;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.Item;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.SingleWarSearch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    AdapterRecyItem adapter;

    EditText inputItem;
    Button buItem;
    ImageView imageThumb;

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

        String thumbURLtwo = "https://i5.walmartimages.com/asr/8e0c3fb1-673b-4b29-9b8a-46cae3e0d917_1.c5d745d0e28796c3f8b53893ea6e064c.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF";
        imageThumb = (ImageView) findViewById(R.id.imaThumbMain) ;

        Picasso.with(this).load(thumbURLtwo).resize(100, 100)
                .into(imageThumb);

    }

    class AsycAPIcalling extends AsyncTask<String, Double, List<Item>> {

        @Override
        protected List<Item> doInBackground(String... strings) {
            WalMartAPI walCalling = new WalMartAPI(strings[0]);
            walCalling.WalMartCall();
            return null;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            SingleWarSearch singleWarSearch = SingleWarSearch.getInstance();
            String strWal = singleWarSearch.getQuery();

            Log.d("Wal in onPostExecute", "//////////////// "+strWal);

            final ArrayList<Item> data = singleWarSearch.getItemList();
            adapter = new AdapterRecyItem(data);
            mRecyclerView.setAdapter(adapter);
        }

    }
}
