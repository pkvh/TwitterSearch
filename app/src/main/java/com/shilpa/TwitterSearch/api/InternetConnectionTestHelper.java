package com.shilpa.TwitterSearch.api;


import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

public class InternetConnectionTestHelper {
    private NetworkTestHelper networkTestHelper;


    public InternetConnectionTestHelper(NetworkTestHelper networkTestHelper) {
        this.networkTestHelper = networkTestHelper;
    }

    public void checkForActiveInternetConnection(final InternetConnectivityCheckCallBack internetConnectivityCheckCallBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (networkTestHelper.isInternetConnectionAvailable()) {
                    passSuccessCallbackInUiThread(internetConnectivityCheckCallBack);
                }else{
                    passErrorCallbackInUiThread(internetConnectivityCheckCallBack,new IOException());
                }
            }
        }).start();
    }

    private void passErrorCallbackInUiThread(final InternetConnectivityCheckCallBack internetConnectivityCheckCallBack, final IOException exception) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                internetConnectivityCheckCallBack.internetConnectivityFailed(exception);
            }
        });
    }

    private void passSuccessCallbackInUiThread(final InternetConnectivityCheckCallBack internetConnectivityCheckCallBack) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                internetConnectivityCheckCallBack.internetConnectivityCheckSucceed();
            }
        });
    }

    public interface InternetConnectivityCheckCallBack {
        void internetConnectivityCheckSucceed();
        void internetConnectivityFailed(IOException exception);
    }
}
