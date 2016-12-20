package com.lxy.pink.data.retrofit;

import com.lxy.pink.data.retrofit.api.ApiAixifanService;
import com.lxy.pink.data.retrofit.api.ApiAppAcfunService;
import com.lxy.pink.data.retrofit.api.DanmuAixifanService;
import com.lxy.pink.data.retrofit.api.MobileAppAcfunService;
import com.lxy.pink.data.retrofit.api.RemoteService;
import com.lxy.pink.data.retrofit.api.SearchAppAcfunService;
import com.lxy.pink.data.retrofit.api.TodoService;
import com.lxy.pink.data.retrofit.api.WeatherService;
import com.lxy.pink.data.retrofit.api.WebapiAppAcfunService;
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

    private OkHttpClient normalClient = new OkHttpClient();
    //        client.interceptors().add(new HeaderInterceptor());
    private static OkHttpClient acClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new ACHeaderInterceptor())
            .build();


    private static RetrofitAPI instance;
    private TodoService todoService;
    private ApiAixifanService mApiAixifanService;
    private ApiAppAcfunService mApiAppAcfunService;
    private DanmuAixifanService mDanmuAixifanService;
    private MobileAppAcfunService mMobileAppAcfunService;
    private SearchAppAcfunService mSearchAppAcfunService;
    private WebapiAppAcfunService mWebapiAppAcfunService;

    public static RetrofitAPI getInstance() {
        synchronized (RetrofitAPI.class) {
            if (instance == null) {
                instance = new RetrofitAPI();
            }
        }
        return instance;
    }

    public <T> T getService(String url, Class<T> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(normalClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    public <T> T getRxJavaService(OkHttpClient client, String url, Class<T> serviceClass) {
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
        synchronized (RetrofitAPI.class) {
            if (this.remoteService == null) {
                this.remoteService = getRxJavaService(normalClient, Config.HOST, RemoteService.class);
            }
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
        synchronized (RetrofitAPI.class) {
            if (this.weatherService == null) {
                this.weatherService = getRxJavaService(normalClient, Config.HOST_WEATHER, WeatherService.class);
            }
        }
        return weatherService;
    }

    @Override
    public TodoService getTodoService() {
        synchronized (RetrofitAPI.class) {
            if (this.todoService == null) {
                this.todoService = new TodoService();
            }
        }
        return todoService;
    }

    @Override
    public ApiAixifanService getApiAixifanService() {
        synchronized (RetrofitAPI.class) {
            if (this.mApiAixifanService == null) {
                this.mApiAixifanService = getRxJavaService(acClient, Config.HOST_ApiAixifan, ApiAixifanService.class);
            }
        }
        return mApiAixifanService;
    }

    @Override
    public ApiAppAcfunService getApiAppAcfunService() {
        synchronized (RetrofitAPI.class) {
            if (this.mApiAppAcfunService == null) {
                this.mApiAppAcfunService = getRxJavaService(acClient, Config.HOST_ApiAppAcfun, ApiAppAcfunService.class);
            }
        }
        return mApiAppAcfunService;
    }

    @Override
    public DanmuAixifanService getDanmuAixifanService() {
        synchronized (RetrofitAPI.class) {
            if (mDanmuAixifanService == null) {
                this.mDanmuAixifanService = getRxJavaService(acClient, Config.HOST_DanmuAixifan, DanmuAixifanService.class);
            }
        }
        return null;
    }

    @Override
    public MobileAppAcfunService getMobileAppAcfunService() {
        synchronized (RetrofitAPI.class) {
            if (mMobileAppAcfunService == null) {
                this.mMobileAppAcfunService = getRxJavaService(acClient, Config.HOST_MobileAppAcfun, MobileAppAcfunService.class);
            }
        }
        return mMobileAppAcfunService;
    }

    @Override
    public SearchAppAcfunService getSearchAppAcfunService() {
        synchronized (RetrofitAPI.class) {
            if (mSearchAppAcfunService == null) {
                mSearchAppAcfunService = getRxJavaService(acClient, Config.HOST_SearchAppAcfun, SearchAppAcfunService.class);
            }
        }
        return mSearchAppAcfunService;
    }

    @Override
    public WebapiAppAcfunService getWebapiAppAcfunService() {
        synchronized (RetrofitAPI.class) {
            if (mWebapiAppAcfunService == null) {
                mWebapiAppAcfunService = getRxJavaService(acClient, Config.HOST_WebapiAppAcfun, WebapiAppAcfunService.class);
            }
        }
        return mWebapiAppAcfunService;
    }

}
