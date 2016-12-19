package com.lxy.pink.data.model.acfun;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACVideoMark {

    /**
     * success : true
     * msg : ok
     * status : 200
     * data : {"exist":false,"isStowed":0,"stowCount":2324}
     */

    private boolean success;
    private String msg;
    private int status;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * exist : false
         * isStowed : 0
         * stowCount : 2324
         */

        private boolean exist;
        private int isStowed;
        private int stowCount;

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }

        public int getIsStowed() {
            return isStowed;
        }

        public void setIsStowed(int isStowed) {
            this.isStowed = isStowed;
        }

        public int getStowCount() {
            return stowCount;
        }

        public void setStowCount(int stowCount) {
            this.stowCount = stowCount;
        }
    }
}
