package com.lxy.pink.ui.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseActivity;
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
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.Launch(SplashActivity.this);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }, Config.SPLASH_DELAY);
//        LayoutTransition transition = new LayoutTransition();
//        mRelativeLayout.setLayoutTransition(transition);
//
//        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f);
//        animIn.setDuration(transition.getDuration(LayoutTransition.APPEARING));
//        animIn.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                View view = (View) ((ObjectAnimator) animation).getTarget();
//                view.setRotation(0f);
//            }
//        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Button button = new Button(SplashActivity.this);
//                button.setText("jdhsjkghfdkjgd");
//                mRelativeLayout.addView(button);
//            }
//        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
