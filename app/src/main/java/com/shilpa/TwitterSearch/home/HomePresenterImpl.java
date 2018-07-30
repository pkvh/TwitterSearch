package com.shilpa.TwitterSearch.home;


import android.util.Base64;
import android.util.Log;

import com.shilpa.TwitterSearch.api.ApiService;
import com.shilpa.TwitterSearch.api.ApiServiceBuilder;
import com.shilpa.TwitterSearch.api.ApiServiceImpl;
import com.shilpa.TwitterSearch.api.InternetConnectionTestHelper;
import com.shilpa.TwitterSearch.api.NetworkTestHelper;
import com.shilpa.TwitterSearch.api.TwitterAppService;
import com.shilpa.TwitterSearch.common.Constants;
import com.shilpa.TwitterSearch.common.TwitterSearchOperationHandler;
import com.shilpa.TwitterSearch.model.SearchResponse;
import com.shilpa.TwitterSearch.model.Token;

import java.net.URLEncoder;

public class HomePresenterImpl implements HomePresenter {
    HomeView homeView;
    ApiService apiService;
    NetworkTestHelper networkTestHelper;
    InternetConnectionTestHelper internetConnectionTestHelper;
    TwitterSearchOperationHandler appOperationHandler;
    TwitterAppService twitterAppService;
    String token;


    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        networkTestHelper = new NetworkTestHelper();
        internetConnectionTestHelper = new InternetConnectionTestHelper(networkTestHelper);
        apiService = new ApiServiceImpl(internetConnectionTestHelper);
        twitterAppService = ApiServiceBuilder.buildService(null, TwitterAppService.class);

        appOperationHandler = TwitterSearchOperationHandler.getTwitterSearchOperationHandler();
    }


    @Override
    public void searchData(String searchItem) {

        String urlApiKey = Constants.ErrorType.TWITTER_USER_API_KEY;
        String urlApiSecret = Constants.ErrorType.TWITTER_USER_API_SECRET;
        String combined = urlApiKey + ":" + urlApiSecret;
        String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);


       if (networkTestHelper.isNetworkConnected()) {

       /*     apiService.Authenticate("Basic " + base64Encoded, new ApiService.Callback<Token>() {
                @Override
                public void onSuccess(Token response) {
                    if (response.getAccess_token().length() > 0) {
                        // Log.e("userId", response.getSearchResponseData().get(0).getUserId());
                        // Log.e("apiKey", response.getSearchResponseData().get(0).getApiKey());
                        // Log.e("status", response.getSearchResponseData().get(0).getStatus());
                        //  Log.e("message", response.getSearchResponseData().get(0).getMessage());
                        // appOperationHandler.setSearchResponseData(response.getTweets().get(0));
                        // homeView.onRequestSuccess(response.getTweets());
                       token= response.getAccess_token();
                        Log.e("TOKENNN",token+"dd");
                    } else {
                        Log.e("TOKENNN",token);
                        // Log.e("FailedauthenticateUser", response.getSearchResponseData().get(0).getStatus());
                        //homeView.onRequestFailure(response.getTweets().get(0).getText());
                    }
                }

                @Override
                public void onError(Throwable error) {
                    Log.e("Failed", "failed on error");
                    Log.e("Failed", error.getMessage());
                    //homeView.clearDataField();
                    //homeView.onRequestFailure(error.getLocalizedMessage());
                }
            });
*/


        apiService.SearchDataList(token, searchItem, new ApiService.Callback<SearchResponse>() {
            @Override
            public void onSuccess(SearchResponse response) {
                if (response.getTweets().size() > 0) {
                    // Log.e("userId", response.getSearchResponseData().get(0).getUserId());
                    // Log.e("apiKey", response.getSearchResponseData().get(0).getApiKey());
                    // Log.e("status", response.getSearchResponseData().get(0).getStatus());
                    //  Log.e("message", response.getSearchResponseData().get(0).getMessage());
                    appOperationHandler.setSearchResponseData(response.getTweets().get(0));
                    homeView.onRequestSuccess(response.getTweets());
                } else {
                    // Log.e("FailedauthenticateUser", response.getSearchResponseData().get(0).getStatus());
                    homeView.onRequestFailure(response.getTweets().get(0).getText());
                }
            }

            @Override
            public void onError(Throwable error) {
                Log.e("Failed", "failed on error");
                Log.e("Failed", error.getMessage());
                //homeView.clearDataField();
                homeView.onRequestFailure(error.getLocalizedMessage());
            }
        });
    }

        else {
            homeView.onRequestFailure(networkTestHelper.ERROR_INTERNET_NOT_CONNECTED);
        }
    }


}

