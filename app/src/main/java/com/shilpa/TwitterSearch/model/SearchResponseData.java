package com.shilpa.TwitterSearch.model;


import com.google.gson.annotations.SerializedName;

public class SearchResponseData {


        @SerializedName("created_at")
        public String dateCreated;

        @SerializedName("id")
        public String id;

        @SerializedName("text")
        public String text;

        @SerializedName("in_reply_to_status_id")
        public String inReplyToStatusId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    @SerializedName("in_reply_to_user_id")
        public String inReplyToUserId;

        @SerializedName("in_reply_to_screen_name")
        public String inReplyToScreenName;

        @Override
        public String  toString(){
            return text;
        }
    }