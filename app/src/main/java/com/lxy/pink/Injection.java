package com.lxy.pink;

import android.content.Context;

import com.lxy.pink.utils.schedulers.BaseSchedulerProvider;
import com.lxy.pink.utils.schedulers.SchedulerProvider;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class Injection {
    public static Context provideContext() {
        return PinkApplication.getInstance();
    }
    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
