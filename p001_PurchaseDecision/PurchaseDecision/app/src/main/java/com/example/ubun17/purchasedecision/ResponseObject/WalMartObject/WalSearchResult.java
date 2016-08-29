package com.example.ubun17.purchasedecision.ResponseObject.WalMartObject;

import java.util.List;

/**
 * Created by ubun17 on 8/27/16.
 */
public class WalSearchResult {
    String mSearchTerm;
    List<Item> mItemList;

    public WalSearchResult() {

    }

    public WalSearchResult(String mSearchTerm, List<Item> mItemList) {
        this.mSearchTerm = mSearchTerm;
        this.mItemList = mItemList;
    }

    public String getmSearchTerm() {
        return mSearchTerm;
    }

    public List<Item> getmItemList() {
        return mItemList;
    }
}
