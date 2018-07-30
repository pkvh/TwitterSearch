package com.shilpa.TwitterSearch.api;


import android.content.Context;
import android.net.ConnectivityManager;

import com.shilpa.TwitterSearch.application.TwitterSearchApplication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkTestHelper {
    public static final String ERROR_INTERNET_NOT_CONNECTED = "Internet not Available";
    public static final String ERROR_SERVICE_UNAVAILABLE = "Service not Available";

    public static final String INTERNET_CHECK_URL = "http://www.google.com";
    public static final int INTERNET_CHECK_TIME_OUT = 6000;
    public static final int RESPONSE_CODE_ERROR_START_RANGE = 400;

    public boolean isInternetConnectionAvailable(){
        HttpURLConnection httpURLConnection;
        int responseCode;

        try {
            httpURLConnection = (HttpURLConnection) new URL(INTERNET_CHECK_URL).openConnection();
            //Time out for reading input stream
            httpURLConnection.setReadTimeout(INTERNET_CHECK_TIME_OUT);
            //Time out for connection.connect
            httpURLConnection.setConnectTimeout(INTERNET_CHECK_TIME_OUT);
            //Set the request Type
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(false);
            httpURLConnection.connect();
            httpURLConnection.setInstanceFollowRedirects(false);
            responseCode = httpURLConnection.getResponseCode();

            if(responseCode >= RESPONSE_CODE_ERROR_START_RANGE){
                return false;
            }

        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean isNetworkConnected() {
        //TODO Replace with a optimised and correct check for this
        ConnectivityManager cm = (ConnectivityManager) TwitterSearchApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean networkConnected = cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected();
        return networkConnected;
    }
}
