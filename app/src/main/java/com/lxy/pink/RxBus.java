package com.lxy.pink;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */


import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 6/25/16
 * Time: 3:01 PM
 * Desc: An EventBus powered by RxJava.
 * But before you use this RxBus, bear in mind this very IMPORTANT note:
 * - Be very careful when error occurred here, this can terminate the whole
 * event observer pattern. If one error ever happened, new events won't be
 * received because this subscription has be terminated after onError(Throwable).
 */
public class RxBus {
    private static final String TAG = "RxBus";
    private static volatile RxBus instance;

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    /**
     * PublishSubject<Object> subject = PublishSubject.create();
     * // observer1 will receive all onNext and onCompleted events
     * subject.subscribe(observer1);
     * subject.onNext("one");
     * subject.onNext("two");
     * // observer2 will only receive "three" and onCompleted
     * subject.subscribe(observer2);
     * subject.onNext("three");
     * subject.onCompleted();
     */
    private PublishSubject<Object> mEventBus = PublishSubject.create();

    public void post(Object event) {
        mEventBus.onNext(event);
    }

    public Observable<Object> toObservable() {
        return mEventBus;
    }

    public static Subscriber<Object> defaultSubscriber() {
        return new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Logger.t(TAG).d("off duty!");
            }

            @Override
            public void onError(Throwable e) {
                Logger.t(TAG).e("RxBus Error!");
            }

            @Override
            public void onNext(Object o) {
                Logger.t(TAG).d("new event received!");
            }
        };
    }

}
