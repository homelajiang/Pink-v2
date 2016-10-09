package com.lxy.pink.data.retrofit;

import com.lxy.pink.data.retrofit.api.RemoteService;
import com.lxy.pink.data.retrofit.api.WeatherService;
import com.lxy.pink.utils.Config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuan on 2016/10/9.
 */

public class RetrofitAPI implements RetrofitApimpl {

    private WeatherService weatherService;
    private RemoteService remoteService;

    private OkHttpClient client = new OkHttpClient();
    //        client.interceptors().add(new HeaderInterceptor());

    private static RetrofitAPI instance;

    public static RetrofitAPI getInstance() {
        if (instance == null) {
            instance = new RetrofitAPI();
        }
        return instance;
    }


    public <T> T getService(String url, Class<T> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    public <T> T getRxJavaService(String url, Class<T> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    /**
     * get remoteService
     *
     * @return
     */
    @Override
    public RemoteService getRemoteService() {
        if (this.remoteService == null) {
            this.remoteService = getRxJavaService(Config.HOST, RemoteService.class);
        }
        return remoteService;
    }

    /**
     * get weatherService
     *
     * @return
     */
    @Override
    public WeatherService getWeatherService() {
        if (this.weatherService == null) {
            this.weatherService = getRxJavaService(Config.HOST_WEATHER, WeatherService.class);
        }
        return weatherService;
    }
}
