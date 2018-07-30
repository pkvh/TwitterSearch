package com.shilpa.TwitterSearch.api;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ErrorTransformingCallback<T> implements Callback<T> {

    public abstract void onSuccessResponse(Response<T> response);
    public abstract void onErrorOccurred(Throwable throwable, Response<T> response);

    private InternetConnectionTestHelper internetConnectionTestHelper;

    public ErrorTransformingCallback(InternetConnectionTestHelper internetConnectionTestHelper) {
        this.internetConnectionTestHelper = internetConnectionTestHelper;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            //Api error occurred. We can safely assume that internet is there since server returned response
            ServiceUnavailableException serviceUnavailableException;
            try {
                serviceUnavailableException = new ServiceUnavailableException(response.code(), response.errorBody().string());
            } catch (IOException e) {
                serviceUnavailableException = new ServiceUnavailableException(response.code(), "");
            }
            onErrorOccurred(serviceUnavailableException, response);

        }
        onSuccessResponse(response);
    }

    @Override
    public void onFailure(Call<T> call, final Throwable throwable) {
       /* Error happened in executing api call. May have happened due to connection error or server error.
          Checking for internet connectivity to determine problem with connection. if no problem wit connection
         then we go for service unavailability*/

        internetConnectionTestHelper.checkForActiveInternetConnection(new InternetConnectionTestHelper.InternetConnectivityCheckCallBack() {
            @Override
            public void internetConnectivityCheckSucceed() {
                ServiceUnavailableException serviceUnavailableException = new ServiceUnavailableException();
                serviceUnavailableException.setStackTrace(throwable.getStackTrace());
                onErrorOccurred(serviceUnavailableException, null);
            }

            @Override
            public void internetConnectivityFailed(IOException exception) {
               
                onErrorOccurred(throwable, null);
            }
        });
    }
}
