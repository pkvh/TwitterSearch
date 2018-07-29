package com.shilpa.TwitterSearch.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.shilpa.TwitterSearch.R;
import com.shilpa.TwitterSearch.utils.SharedPreferencesManager;

public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SharedPreferencesManager.getInstance().isLoggedIn()) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                } else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
