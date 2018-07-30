package com.shilpa.TwitterSearch.api;


import com.shilpa.TwitterSearch.model.SearchResponse;
import com.shilpa.TwitterSearch.model.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TwitterAppService {


    @GET("1.1/search/tweets.json?q=ClearTax")
    Call<SearchResponse> searchDataList(@Header("Authorization") String auth);

}
