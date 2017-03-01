package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.acfun.ACBananaCheck;
import com.lxy.pink.data.model.acfun.ACChannel;
import com.lxy.pink.data.model.acfun.ACFunInfo;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.data.model.acfun.ACSplash;
import com.lxy.pink.data.model.acfun.ACVideoInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
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

    //    http://api.aixifan.com/videos/3378857
    @GET("videos/{contentId}")
    Observable<ACVideoInfo> getVideoInfo(
            @Query("contentId") int contentId
    );

    //http://api.aixifan.com/open/phone/source
    @GET("open/phone/source")
    Observable<ACSplash> getSplash();

    //http://api.aixifan.com/videos/3326059
    @GET("videos/{videoId}")
    Observable<ACVideoInfo> getVideoInfo(
            @Path("videoId") String videoId
    );

    //http://api.aixifan.com/bangumis/1480028?page={num:1,size:50}
    @GET("bangumis/{funId}")
    Observable<ACFunInfo> getFunInfo(
            @Path("funId") String funId,
            @Query("page") String page
    );

    //http://api.aixifan.com/channels/allChannels
    @GET("channels/allChannels")
    Observable<ACChannel> getAllChannel();

    //投香蕉前的检查
    //http://api.aixifan.com/bananas/remain?access_token=x7BIgaA7sT6OjKmfUzgxoQLJi9e41HBX
    @GET("bananas/remain")
    Observable<ACBananaCheck> checkBanana(
            @Query("access_token") String access_token
    );
}
