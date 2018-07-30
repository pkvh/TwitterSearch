package com.shilpa.TwitterSearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    @SerializedName("statuses")
    public ArrayList<SearchResponseData> tweets;

    public ArrayList<SearchResponseData> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<SearchResponseData> tweets) {
        this.tweets = tweets;
    }


}