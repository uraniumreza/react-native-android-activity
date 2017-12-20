package com.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.facebook.react.ReactActivity;

public class ExampleActivity extends ReactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                      | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                      | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                      | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                      | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        findViewById(R.id.call_callback_btn).setEnabled(true);
        findViewById(R.id.go_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.call_callback_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityStarterModule.triggerAlert("Hello from " + getClass().getSimpleName());
            }
        });
    }
}
