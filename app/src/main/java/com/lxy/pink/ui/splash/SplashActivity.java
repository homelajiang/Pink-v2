package com.lxy.pink.ui.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseActivity;
import com.lxy.pink.ui.main.MainActivity;
import com.lxy.pink.utils.Config;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
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
