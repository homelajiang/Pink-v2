package com.lxy.lodingview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final OWLoadingView ow = (OWLoadingView) findViewById(R.id.ow);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ow.startAnim();
            }
        },3000);
    }
}
