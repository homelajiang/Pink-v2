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

/**
 * Created by yuan on 2016/10/9.
 */

public interface RetrofitApimpl {

    RemoteService getRemoteService();

    WeatherService getWeatherService();

    TodoService getTodoService();

    ApiAixifanService getApiAixifanService();

    ApiAppAcfunService getApiAppAcfunService();

    DanmuAixifanService getDanmuAixifanService();

    MobileAppAcfunService getMobileAppAcfunService();

    SearchAppAcfunService getSearchAppAcfunService();

    WebapiAppAcfunService getWebapiAppAcfunService();
}
