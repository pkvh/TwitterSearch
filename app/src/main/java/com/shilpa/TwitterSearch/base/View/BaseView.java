package com.shilpa.TwitterSearch.base.View;

public interface BaseView {

    void showLoading();
    void hideLoading();
    void showToast(String message);

    void onRequestFailure(String message);
    void onRequestSuccess();
}
