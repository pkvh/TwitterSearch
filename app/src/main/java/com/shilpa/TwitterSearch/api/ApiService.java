package com.shilpa.TwitterSearch.api;


import com.shilpa.TwitterSearch.model.SearchResponse;
import com.shilpa.TwitterSearch.model.Token;

public interface ApiService {
    void SearchDataList(String auth,String searchData,Callback<SearchResponse> callback);

    void Authenticate(String key, Callback<Token> callback);


    interface Callback<T>{
        void onSuccess(T response);
        void onError(Throwable error);
    }
}
