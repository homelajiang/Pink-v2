package com.lxy.pink.data.retrofit.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by homelajiang on 2016/12/19 0019.
 */

public interface DanmuAixifanService {

    /**
     * 获取弹幕String
     *
     * @param danmakuId 弹幕id
     * @param p1        0
     * @param p2        500
     * @return
     */
    //http://danmu.aixifan.com/V4/4913210/0/500
    @GET("V4/{danmakuId}/{p1}/{p2}")
    Observable<String> getDadnmu(
            @Path("danmakuId") int danmakuId,
            @Path("p1") int p1,
            @Path("p2") int p2
    );
}
