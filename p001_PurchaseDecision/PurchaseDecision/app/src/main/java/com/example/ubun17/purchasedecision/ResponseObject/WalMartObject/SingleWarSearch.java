package com.example.ubun17.purchasedecision.ResponseObject.WalMartObject;

/**
 * Created by ubun17 on 8/26/16.
 */
public class SingleWarSearch {
    private static SingleWarSearch walSingleton = null;
    private static String query;
    //private static List<Item> itemList;

    public SingleWarSearch() {
        query = "testtttttttttttttttttttt";
    }

    public static SingleWarSearch getInstance() {
        if (walSingleton == null) {
            walSingleton = new SingleWarSearch();
        }
        return walSingleton;
    }

    public void setWalSingleton(String str) {
        query = str;
        //itemList = arry;
    }

    public String getQuery() {
        return query;
    }

}
