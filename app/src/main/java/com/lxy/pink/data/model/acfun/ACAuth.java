package com.lxy.pink.data.model.acfun;

/**
 * Created by homelajiang on 2017/2/16 0016.
 */

public class ACAuth {

    /**
     * access_token : d9Gaxrn6iJZ0w8KOfyqNBAn7zzJDMCs9
     * userImg : http://cdn.aixifan.com/dotnet/20120923/style/image/avatar.jpg
     * expires : 1488100061000
     * userGroupLevel : 1
     * mobileCheck : 0
     * userId : 784105
     * username : 洛阳一枝花
     */

    private String access_token;
    private String userImg;
    private long expires;
    private int userGroupLevel;
    private int mobileCheck;
    private long userId;
    private String username;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public int getUserGroupLevel() {
        return userGroupLevel;
    }

    public void setUserGroupLevel(int userGroupLevel) {
        this.userGroupLevel = userGroupLevel;
    }

    public int getMobileCheck() {
        return mobileCheck;
    }

    public void setMobileCheck(int mobileCheck) {
        this.mobileCheck = mobileCheck;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
