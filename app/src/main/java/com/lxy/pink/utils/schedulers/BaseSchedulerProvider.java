package com.lxy.pink.utils.schedulers;

/**
 * Created by homelajiang on 2017/5/17 0017.
 */

import android.support.annotation.NonNull;

import rx.Scheduler;

/**
 * Allow providing different types of {@link rx.Scheduler}s
 */
public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
