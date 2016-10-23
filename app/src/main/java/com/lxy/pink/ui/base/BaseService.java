package com.lxy.pink.ui.base;

import android.app.Service;
import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/10/22.
 */

public abstract class BaseService extends Service {
    private CompositeSubscription mSubscriptions;

    @Override
    public void onCreate() {
        super.onCreate();
        addSubscription(subscribeEvents());
    }

    protected Context getContext() {
        return this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
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
}
