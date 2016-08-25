package com.example.ubun17.finalwalmart00.WalMartSearch;

/**
 * Created by ubun17 on 8/24/16.
 */
public class WalSearchSingleton {
    private static WalSearchSingleton walSingleton = null;
    private static WalMartSearch walMartSearch;

    public WalSearchSingleton() {
        walMartSearch = new WalMartSearch();
    }

    public static WalSearchSingleton getInstance() {
        if (walSingleton == null) {
            walSingleton = new WalSearchSingleton();
        }
        return walSingleton;
    }

    public void setWalSingleton(WalMartSearch instance) {
        walMartSearch = instance;
    }

    public WalMartSearch getWalMartSearch() {
        return walMartSearch;
    }
}
