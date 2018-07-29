package com.shilpa.TwitterSearch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.shilpa.TwitterSearch.R;
import com.shilpa.TwitterSearch.base.activty.TwitterBaseActivity;
import com.shilpa.TwitterSearch.common.Constants;
import com.shilpa.TwitterSearch.login.LoginPresenter;
import com.shilpa.TwitterSearch.login.LoginPresenterImpl;
import com.shilpa.TwitterSearch.login.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends TwitterBaseActivity implements LoginView{

    @BindView(R.id.parent_id)
    AutoCompleteTextView parentIdView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    public LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
       /* mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });*/


    }
    public void onSignInButtonClicked(View view) {
        showLoading();
        String parentId = parentIdView.getText().toString();
        String password = mPasswordView.getText().toString();
        loginPresenter.validateLogin(parentId, password);

    }

    @Override
    public void showLoading() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRequestFailure(String message) {
      hideLoading();

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
      //showToast(message);
    }

    @Override
    public void onRequestSuccess() {
        hideLoading();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onValidationError(String errorType) {
        hideLoading();
        if(errorType.equals(Constants.ErrorType.EMPTY_PARENT_ID)){
            showToast(getString(R.string.error_invalid_parent_id));
        }else if(errorType.equals(Constants.ErrorType.EMPTY_PASSWORD)){
            showToast(getString(R.string.error_invalid_password));
        }
    }

    @Override
    public void onValidationSuccess() {
        String parentId = parentIdView.getText().toString();
        String password = mPasswordView.getText().toString();
        loginPresenter.requestLogin(parentId, password);
    }

    @Override
    public void clearDataField(){
        parentIdView.setText("");
        mPasswordView.setText("");
        parentIdView.requestFocus();
    }

}

