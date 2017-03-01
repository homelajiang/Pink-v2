package com.lxy.pink.data.model.acfun;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACActionFollow {

    /**
     * success : true
     * followedCount : 90996
     * username : STN快报
     */

    private boolean success;
    private int followedCount;
    private String username;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getFollowedCount() {
        return followedCount;
    }

    public void setFollowedCount(int followedCount) {
        this.followedCount = followedCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
