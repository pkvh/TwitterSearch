package com.shilpa.TwitterSearch.common;


import com.shilpa.TwitterSearch.model.SearchResponseData;
import com.shilpa.TwitterSearch.utils.SharedPreferencesManager;

public class TwitterSearchOperationHandler {

    private static TwitterSearchOperationHandler twitterSearchOperationHandler;
    private SearchResponseData searchResponseData;

    private SharedPreferencesManager sharedPreferencesManager;

    private TwitterSearchOperationHandler() {
        sharedPreferencesManager = SharedPreferencesManager.getInstance();
    }

    public static TwitterSearchOperationHandler getTwitterSearchOperationHandler() {
        return getInstance();
    }

    public static TwitterSearchOperationHandler getInstance() {
        if (twitterSearchOperationHandler == null) {
            twitterSearchOperationHandler = new TwitterSearchOperationHandler();
        }
        return twitterSearchOperationHandler;
    }


    public SearchResponseData getSearchResponseData() {

        return searchResponseData;
    }
    public void setSearchResponseData(SearchResponseData searchResponseData) {

        this.searchResponseData = searchResponseData;
    }

   /* public void logout(){
        sharedPreferencesManager.clearLoginData();
        sharedPreferencesManager.setIsLoggedIn(false);
        this.searchResponseData = null;
    }*/


}
