package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.data.model.acfun.ACVideoMark;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * check video whether marked
     *
     * @param contentId
     * @param UserId
     * @param access_token
     * @return
     */
    //http://api.app.acfun.cn/apiserver/user/fav/content/exist?contentId=3506658&userId=784105&access_token=x7BIgaA7sT6OjKmfUzgxoQLJi9e41HBX
    @GET("apiserver/user/fav/content/exist")
    Observable<ACVideoMark> checkVideoMark(
            @Query("contentId") int contentId,
            @Query("userId") int UserId,
            @Query("access_token") String access_token
    );

    /**
     * mark or unRemark video
     *
     * @param action       "add "or "remove"
     * @param access_token
     * @param userId
     * @param contentId
     * @return
     */
    //http://api.app.acfun.cn/apiserver/user/fav/content/add
    @FormUrlEncoded
    @POST("apiserver/user/fav/content/{action}")
    Observable<ACVideoMark> videoMark(
            @Path("action") String action,
            @Field("access_token") String access_token,
            @Field("userId") int userId,
            @Field("contentId") int contentId
    );

    //http://api.app.acfun.cn/apiserver/user/contribution?pageNo=1&pageSize=20&userId=614361&type=1&sort=2
    @GET("apiserver/user/contribution")
    Observable<ACUserContribute> getUserContribute(
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize,
            @Query("userId") int userId,
            @Query("type") int type,
            @Query("sort") int sort
    );
}
