package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.acfun.ACChannel;
import com.lxy.pink.data.model.acfun.ACFun;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.acfun.ACSplash;
import com.lxy.pink.data.model.acfun.ACVideo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by homelajiang on 2016/12/19 0019.
 */

public interface ApiAppAcfunService {
    //http://api.app.acfun.cn/apiserver/profile?userId=784105
    @GET("apiserver/profile")
    Observable<ACProfile> getUserInfo(
            @Query("userId") String userId
    );
    //http://api.aixifan.com/open/phone/source
    @GET("open/phone/source")
    Observable<ACSplash> getSplash();

    //http://api.aixifan.com/videos/3326059
    @GET("videos/{videoId}")
    Observable<ACVideo> getVideoInfo(
            @Path("videoId") String videoId
    );

    //http://api.aixifan.com/bangumis/1480028?page={num:1,size:50}
    @GET("bangumis/{funId}")
    Observable<ACFun> getFunInfo(
            @Path("funId") String funId,
            @Query("page") String page
    );

    //http://api.aixifan.com/channels/allChannels
    @GET("channels/allChannels")
    Observable<ACChannel> getAllChannel();
}
