package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.acfun.ACRecommend;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by homelajiang on 2016/12/19 0019.
 */

public interface ApiAixifanService {
    //http://api.aixifan.com/regions?belong=0&loadCount=-1
    @GET("regions")
    Observable<ACRecommend> getRecommend(
            @Query("belong") int belong,
            @Query("loadCount") int loadCount
    );
}
