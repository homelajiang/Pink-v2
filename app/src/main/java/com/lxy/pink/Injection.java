package com.lxy.pink;

import android.content.Context;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class Injection {
    public static Context provideContext() {
        return PinkApplication.getInstance();
    }
}
