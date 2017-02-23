package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.acfun.ACBaseModel;
import com.lxy.pink.data.model.acfun.ACSign;
import com.lxy.pink.data.model.acfun.ACVideoMark;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by homelajiang on 2016/12/19 0019.
 */

public interface WebapiAppAcfunService {
    //http://webapi.app.acfun.cn/record/actions/signin?channel=1&access_token=ndHvqKWDxHgY2nW1IzcvNkSs3OMEMmZw
    @GET("record/actions/signin")
    Observable<ACBaseModel> checkSign(
            @Query("channel") String channel,
            @Query("access_token") String access_token
    );

    //http://webapi.app.acfun.cn/record/actions/signin?channel=1&access_token=d9Gaxrn6iJZ0w8KOfyqNBAn7zzJDMCs9
    @FormUrlEncoded
    @POST("record/actions/signin")
    Observable<ACSign> sign(
            @Query("access_token") String access_token,
            @Field("channel") String channel
    );

    //http://api.app.acfun.cn/apiserver/user/fav/content/exist?contentId=3326059&userId=784105&access_token=ndHvqKWDxHgY2nW1IzcvNkSs3OMEMmZw
    @GET("apiserver/user/fav/content/exist")
    Observable<ACVideoMark> checkVideoMarked(
            @Query("contentId") String contentId,
            @Query("userId") String userId,
            @Query("access_token") String access_token
    );

}
