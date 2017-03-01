package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.acfun.ACVideoSearchLike;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by homelajiang on 2016/12/19 0019.
 */

public interface SearchAppAcfunService {

    /**
     * search by video Id ,id is the video content di add "ac" examle:"ac3506658"
     *
     * @param id
     * @param pageSize
     * @param pageNo
     * @param type     default 1
     * @return
     */
    //http://search.app.acfun.cn/like?id=ac3506658&pageSize=6&pageNo=1&type=1
    @GET("like")
    Observable<ACVideoSearchLike> searchByVideoId(
            @Query("id") String id,
            @Query("pageSize") int pageSize,
            @Query("pageNo") int pageNo,
            @Query("type") int type
    );

}
