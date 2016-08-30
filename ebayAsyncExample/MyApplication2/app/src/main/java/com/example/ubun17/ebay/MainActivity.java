package com.example.ubun17.ebay;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String ebayURL = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=WanLim-priceCom-PRD-59a67cc35-c54e5d44&GLOBAL-ID=EBAY-US&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&keywords=harry%20potter&paginationInput.entriesPerPage=3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Log.d("ssss", "working_____________________________");

        String anyString = "asdf";

        AsycAPIcalling asycAPIcalling = new AsycAPIcalling();
        asycAPIcalling.execute(anyString);

    }//End of onCreate



    class AsycAPIcalling extends AsyncTask<String, Double, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            String asdf = strings[0];

            APIcall apIcall = new APIcall();
            //apIcall.APIresponseST();

            APIWalMart apiWalMart= new APIWalMart();
            //apiWalMart.APIresponseST();

            Log.d("I am ", "///////////////////// in AsyAPIcalling");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("adddd");
            return arrayList ;
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);

            testAnyFunction(values[0]);

        }


        @Override
        protected void onPostExecute(ArrayList<String> arrayList) {//This integer comes from doInBackground return
            //super.onPostExecute(asss);

            //Use arrlist for update View

        }
    }

    protected Double testAnyFunction(Double num) {
        return num;
    }

}
