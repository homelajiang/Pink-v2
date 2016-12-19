package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.acfun.ACUser;
import com.lxy.pink.data.model.acfun.ACVideoMark;

import retrofit2.http.GET;
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

    //http://mobile.app.acfun.cn/api/friend.aspx?name=checkFollow&userId=1175322&access_token=ndHvqKWDxHgY2nW1IzcvNkSs3OMEMmZw
    @GET("api/friend.aspx")
    Observable<ACUser> checkUserFollow(
            @Query("checkFollow") String checkFollow,
            @Query("userId") String userId,
            @Query("access_token") String access_token
    );
}
