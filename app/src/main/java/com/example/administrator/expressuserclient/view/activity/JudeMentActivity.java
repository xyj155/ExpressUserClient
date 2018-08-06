package com.example.administrator.expressuserclient.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2018/8/6/006.
 */

public class JudeMentActivity extends AppCompatActivity {
    private static final String TAG = "JudeMentActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        boolean login = sp.getBoolean("login", false);
        Log.i(TAG, "onCreate: "+login);
        if (login) {
            startActivity(new Intent(JudeMentActivity.this, SplashActivity.class));
            finish();
        } else {
            startActivity(new Intent(JudeMentActivity.this, IntroduceActivity.class));
            finish();
        }
    }
}
