package com.lxy.pink.data.model.acfun;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoCommentRes {

    /**
     * captcha : false
     * success : true
     * commentId : 73407456
     * status : 0
     */

    private boolean captcha;
    private boolean success;
    private int commentId;
    private int status;

    public boolean isCaptcha() {
        return captcha;
    }

    public void setCaptcha(boolean captcha) {
        this.captcha = captcha;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
