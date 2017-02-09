package com.lxy.pink;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import okhttp3.OkHttpClient;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class PinkApplication extends Application {
    private static PinkApplication instance;

    public static PinkApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

//        CalligraphyConfig.initDefault(
//                new CalligraphyConfig.Builder()
//                        .setDefaultFontPath("fonts/Roboto-Monospace-Regular.ttf")
//                        .setFontAttrId(R.attr.fontPath)
//                        .build()
//        );


        if (!LeakCanary.isInAnalyzerProcess(this))
            LeakCanary.install(this);

        Logger
                .init("PINK")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
//                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL   Note: Use LogLevel.NONE for the release versions.
                .methodOffset(2)                // default 0
//                .logAdapter(new AndroidLogAdapter()) //default AndroidLogAdapter
        ;

        OkHttpClient okHttpClient = new OkHttpClient();
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                .build();
        Fresco.initialize(this, config);
    }
}
