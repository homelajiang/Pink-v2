package com.lxy.pink.data.retrofit;

import com.lxy.pink.utils.AcHeader;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yuan on 2016/12/21.
 */

public class ACHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;
        newRequest = request.newBuilder()
                .addHeader("appVersion", AcHeader.appVersion)
                .addHeader("deviceType", AcHeader.deviceType)
                .addHeader("market", AcHeader.market)
                .addHeader("productId", AcHeader.productId)
                .addHeader("resolution", AcHeader.resolution)
                .addHeader("udid", AcHeader.udid)
                .addHeader("uuid", AcHeader.uuid)
                .build();
        return chain.proceed(newRequest );
    }
}
