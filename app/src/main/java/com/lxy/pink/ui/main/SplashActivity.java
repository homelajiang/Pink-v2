package com.lxy.pink.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseActivity;
import com.lxy.pink.utils.Config;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class SplashActivity extends BaseActivity {
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getContext().startActivity(new Intent(getContext(), MainActivity.class));
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }, Config.SPLASH_DELAY);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
