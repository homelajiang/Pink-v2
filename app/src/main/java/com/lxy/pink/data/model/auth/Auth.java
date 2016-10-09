package com.lxy.pink.data.model.auth;


import com.lxy.pink.data.model.BaseModel;

/**
 * Created by homelajiang on 2016/8/17 0017.
 */
public class Auth extends BaseModel {

    private String username;
    private String privateKey;
    private String accessToken;
    private String profileId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

}
