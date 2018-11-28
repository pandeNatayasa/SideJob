package com.tr.nata.sidejob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeIntent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(homeIntent);

                finish();
            }
        },SPLASH_TIME_OUT);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
