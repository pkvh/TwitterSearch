package com.shilpa.TwitterSearch.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.shilpa.TwitterSearch.application.TwitterSearchApplication;
import com.shilpa.TwitterSearch.model.SearchResponseData;

public class SharedPreferencesManager {

    private  static final String SCHOOL_APP_PREFERENCES = "school-app-preference";

    private static final String PREF_KEY_USER_LOGGED_IN = "user-logged-in";
    private static final String PREF_KEY_API_KEY = "api-key";
    private static final String PREF_KEY_USER_ID = "user-id";

    private SharedPreferences sharedPrefs;
    private static SharedPreferencesManager sharedPreferencesManagerInstance;
    private Context context;

    private SharedPreferencesManager(){
        context = TwitterSearchApplication.getContext();
        sharedPrefs = context.getApplicationContext().getSharedPreferences(SCHOOL_APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(){
        if(sharedPreferencesManagerInstance == null)
            sharedPreferencesManagerInstance = new SharedPreferencesManager();
        return sharedPreferencesManagerInstance;
    }

    /*public boolean isLoggedIn(){
        return sharedPrefs.getBoolean(PREF_KEY_USER_LOGGED_IN, false);
    }
*/
   /* public void setIsLoggedIn(boolean value){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(PREF_KEY_USER_LOGGED_IN, value);
        editor.apply();
    }*/

    /*public void setLoginData(SearchResponseData loginData){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(PREF_KEY_API_KEY, loginData.getApiKey());
        editor.putString(PREF_KEY_USER_ID, loginData.getUserId());
        editor.apply();
    }*/

   /* public void clearLoginData(){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(PREF_KEY_API_KEY, null);
        editor.putString(PREF_KEY_USER_ID, null);
        editor.apply();
    }*/

   /* public SearchResponseData getLoginData(){
        SearchResponseData searchResponseData = new SearchResponseData();
        searchResponseData.setApiKey(sharedPrefs.getString(PREF_KEY_API_KEY, null));
        searchResponseData.setUserId(sharedPrefs.getString(PREF_KEY_USER_ID, null));
        return searchResponseData;
    }*/

}
