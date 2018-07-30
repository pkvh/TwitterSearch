package com.shilpa.TwitterSearch.home;


import com.shilpa.TwitterSearch.model.SearchResponseData;

import java.util.ArrayList;

public interface HomeView {

    void onRequestSuccess(ArrayList<SearchResponseData> tweets);

    void onRequestFailure(String message);
}
