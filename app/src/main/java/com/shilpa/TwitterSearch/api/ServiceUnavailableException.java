package com.shilpa.TwitterSearch.api;


import java.io.IOException;

public class ServiceUnavailableException extends IOException{

    private int responseCode;
    private String errorBody;

    public ServiceUnavailableException(){
        super(NetworkTestHelper.ERROR_SERVICE_UNAVAILABLE);
        responseCode = -1;
        errorBody = null;
    }

    public ServiceUnavailableException(int responseCode, String errorBody){
        super(NetworkTestHelper.ERROR_SERVICE_UNAVAILABLE);
        this.responseCode = responseCode;
        this.errorBody = errorBody;
    }

    public int getResponseCode() {
        return responseCode;
    }


    public String getErrorBody() {
        return errorBody;
    }
}
