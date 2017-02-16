package com.lxy.pink.data.model.acfun;

/**
 * Created by homelajiang on 2017/2/16 0016.
 */

public class ACAuthRes {

    /**
     * data : {"access_token":"d9Gaxrn6iJZ0w8KOfyqNBAn7zzJDMCs9","userImg":"http://cdn.aixifan.com/dotnet/20120923/style/image/avatar.jpg","expires":1488100061000,"userGroupLevel":1,"mobileCheck":0,"userId":784105,"username":"洛阳一枝花"}
     * success : true
     * status : 200
     */

    private ACAuth data;
    private boolean success;
    private int status;

    public ACAuth getData() {
        return data;
    }

    public void setData(ACAuth data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
