package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.acfun.ACActionFollow;
import com.lxy.pink.data.model.acfun.ACAuthRes;
import com.lxy.pink.data.model.acfun.ACBananaInfo;
import com.lxy.pink.data.model.acfun.ACBananaPostRes;
import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
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

public interface MobileAppAcfunService {
    //http://mobile.app.acfun.cn/bangumi/stow/isStowed?bangumiId=1480028&access_token=ndHvqKWDxHgY2nW1IzcvNkSs3OMEMmZw
    @GET("bangumi/stow/isStowed")
    Observable<ACVideoMark> checkFunMark(
            @Query("bangumiId") String bangumiId,
            @Query("access_token") String access_token
    );

    /**
     * check whether follow or other
     *
     * @param name         "checkFollow"
     * @param userId
     * @param access_token
     * @return
     */
    //http://mobile.app.acfun.cn/api/friend.aspx?name=checkFollow&userId=1175322&access_token=ndHvqKWDxHgY2nW1IzcvNkSs3OMEMmZw
    @GET("api/friend.aspx")
    Observable<ACCheckFollow> checkFollow(
            @Query("name") String name,
            @Query("userId") int userId,
            @Query("access_token") String access_token
    );

    /**
     * name is "follow" or ""unfollow
     *
     * @param name
     * @param userId
     * @param accessToken
     * @return
     */
    //http://mobile.app.acfun.cn/api/friend.aspx?name=unfollow
    @FormUrlEncoded
    @POST("api/friend.aspx")
    Observable<ACActionFollow> actionFollow(
            @Query("name") String name,
            @Field("userId") int userId,
            @Field("access_token") String accessToken
    );


    //http://mobile.app.acfun.cn/oauth2/authorize2.aspx
//    登录
    @POST("oauth2/authorize2.aspx")
    @FormUrlEncoded
    Observable<ACAuthRes> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("response_type") String response_type,
            @Field("client_id") String client_id

    );

    //http://mobile.app.acfun.cn/member/unRead.aspx?uid=784105&access_token=d9Gaxrn6iJZ0w8KOfyqNBAn7zzJDMCs9
    @GET("member/unRead.aspx")
    Observable<Object> getUnReadCount(
            @Query("uid") String uid,
            @Query("accessToken") String accessToken
    );

    //获取香蕉信息
    //http://mobile.app.acfun.cn/banana/getBananaCount.aspx?access_token=x7BIgaA7sT6OjKmfUzgxoQLJi9e41HBX
    @GET("banana/getBananaCount.aspx")
    Observable<ACBananaInfo> getBananaInfo(
            @Query("access_token") String access_token
    );

    //投蕉
    //http://mobile.app.acfun.cn/banana/throwBanana.aspx
    @FormUrlEncoded
    @POST("banana/throwBanana.aspx")
    Observable<ACBananaPostRes> actionBanana(
            @Field("access_token") String access_token,
            @Field("userId") int userId,
            @Field("count") int count,
            @Field("contentId") int contentId
    );

    /**
     * get video comments
     *
     * @param version   4
     * @param contentId
     * @param pageSize  default 50
     * @param pageNo
     * @return
     */
    //获取评论信息
    //http://mobile.app.acfun.cn/comment/content/list?version=4&contentId=3506658&pageSize=50&pageNo=1
    @GET("comment/content/list")
    Observable<ACVideoCommentData> getVideoComment(
            @Query("version") int version,
            @Query("contentId") int contentId,
            @Query("pageSize") int pageSize,
            @Query("pageNo") int pageNo
    );

    /**
     * 发送评论
     *
     * @param text         内容
     * @param quoteId
     * @param contentId
     * @param source       "mobile"
     * @param access_token
     * @param userId
     * @param captcha      ""
     * @return
     */
    //http://mobile.app.acfun.cn/comment.aspx
    @FormUrlEncoded
    @POST("comment.aspx")
    Observable<ACVideoCommentRes> sendVideoComment(
            @Field("text") String text,
            @Field("quoteId") int quoteId,
            @Field("contentId") int contentId,
            @Field("source") String source,
            @Field("access_token") String access_token,
            @Field("userId") int userId,
            @Field("captcha") String captcha
            );

}
