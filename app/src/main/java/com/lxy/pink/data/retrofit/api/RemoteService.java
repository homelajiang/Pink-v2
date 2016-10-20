package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.utils.Config;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by homelajiang on 2016/8/18 0018.
 */
public interface RemoteService {

    //Auth

    @FormUrlEncoded
    @POST("signIn")
    Observable<Auth> signIn(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("signUp")
    Observable<Auth> signUp(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("user/{profileId}")
    Observable<Profile> queryProfile(
            @Path("profileId") String profileId);


    @POST("user/upload")
    Observable<Profile> updateProfile(
            @Body Profile profile);

//    @PUT("")
//    Observable<BaseModel> updateLocation(
//
//    );

}
