package com.lxy.pink.ui.base;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.Window;

import com.lxy.pink.R;
import com.lxy.pink.utils.GradientUtils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private CompositeSubscription mSubscriptions;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenHeight = displayMetrics.heightPixels;

//        Window window = getWindow();
//        GradientDrawable gradientBackgroundDrawable = GradientUtils.create(
//                ContextCompat.getColor(this, R.color.mp_theme_dark_blue_gradientColor),
//                ContextCompat.getColor(this, R.color.mp_theme_dark_blue_background),
//                screenHeight / 2, // (int) Math.hypot(screenWidth / 2, screenHeight / 2),
//                0.5f,
//                0.5f
//        );
//        window.setBackgroundDrawable(gradientBackgroundDrawable);
//        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSubscription(subscribeEvents());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected ActionBar supportActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        return actionBar;
    }

    protected void addSubscription(Subscription subscription) {
        if (subscription == null)
            return;
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeSubscription();
        }
        mSubscriptions.add(subscription);
    }

    protected Subscription subscribeEvents() {
        return null;
    }

    public Context getContext(){
        return this;
    }
}
