package com.example.ubun17.purchasedecision.ResponseObject.WalMartObject;

import java.util.ArrayList;

/**
 * Created by ubun17 on 8/26/16.
 */
public class SingleWarSearch {
    private static SingleWarSearch walSingleton = null;
    private static String query;
    private static ArrayList<Item> itemList;

    public SingleWarSearch() {
        query = "testtttttttttttttttttttt";
    }

    public static SingleWarSearch getInstance() {
        if (walSingleton == null) {
            walSingleton = new SingleWarSearch();
        }
        return walSingleton;
    }

    public void setWalSingleton(String str, ArrayList<Item> itemArray) {
        query = str;
        itemList = itemArray;
    }

    public void setQuery(String str) {
        query = str;
    }

    public String getQuery() {
        return query;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }
}
