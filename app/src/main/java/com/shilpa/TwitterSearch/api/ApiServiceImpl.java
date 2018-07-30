package com.shilpa.TwitterSearch.api;

import android.util.Base64;
import android.util.Log;

import com.shilpa.TwitterSearch.common.Constants;
import com.shilpa.TwitterSearch.model.SearchResponse;
import com.shilpa.TwitterSearch.model.Token;

import retrofit2.Response;


public class ApiServiceImpl implements ApiService {
    private TwitterAppService twitterAppService;
    private InternetConnectionTestHelper internetConnectionTestHelper;

    public ApiServiceImpl(InternetConnectionTestHelper internetConnectionTestHelper){
        twitterAppService = ApiServiceBuilder.buildService(null, TwitterAppService.class);
        this.internetConnectionTestHelper = internetConnectionTestHelper;
    }

    @Override
    public void SearchDataList(String auth,String data, final Callback<SearchResponse> callback ) {
String headerData;
    String nounce= Base64.encodeToString((Constants.ErrorType.TWITTER_USER_API_KEY+":"+Constants.ErrorType.TWITTER_USER_API_SECRET).getBytes(),Base64.URL_SAFE);
headerData="OAuth "+"oauth_consumer_key="+"\""+Constants.ErrorType.TWITTER_USER_API_KEY+"\",oauth_nonce=\""+nounce+"\", oauth_signature=\"" + nounce + "\",oauth_signature_method=\"HMAC-SHA1\"," +
        "oauth_timestamp=\"1318622958\",oauth_token="+"\""+Constants.ErrorType.TWITTER_ACCESS_KEY+"\",oauth_version=\"1.0\"";
Log.e("header",headerData+"");
        twitterAppService.searchDataList(headerData).enqueue(new ErrorTransformingCallback<SearchResponse>(internetConnectionTestHelper) {
            @Override
            public void onSuccessResponse(Response<SearchResponse> response) {
                if(response.body() != null) {
                    callback.onSuccess(response.body());
                } else{
                    callback.onError(new Throwable(response.message()));
                }
//                Log.e("SearchResponseData", "ss"+response.body().getTweets().size());
            }

            @Override
            public void onErrorOccurred(Throwable throwable, Response<SearchResponse> response) {
                callback.onError(throwable);
            }

        });
    }

    @Override
    public void Authenticate(String key, Callback<Token> callback) {

    }

   /* @Override
    public void Authenticate(String key, final Callback<Token> callback) {

        twitterAppService.getToken(key,"application/x-www-form-urlencoded;charset=UTF-8","client_credentials").enqueue(new ErrorTransformingCallback<Token>(internetConnectionTestHelper) {
            @Override
            public void onSuccessResponse(Response<Token> response) {
                if(response.body() != null) {
                    Log.e("TokenResponseData", "ss"+response.body().getAccess_token());
                    callback.onSuccess(response.body());
                } else{
                    callback.onError(new Throwable(response.message()));
                }
//                Log.e("SearchResponseData", "ss"+response.body().getTweets().size());
            }

            @Override
            public void onErrorOccurred(Throwable throwable, Response<Token> response) {
                callback.onError(throwable);
            }

        });

    }*/
}
