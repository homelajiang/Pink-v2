package com.lxy.pink.data.model.acfun;

/**
 * Created by homelajiang on 2017/2/23 0023.
 */

public class ACSign {


    /**
     * code : 200
     * data : {"count":3,"msg":"签到成功，已领取3蕉"}
     * message : OK
     */

    private int code;
    private DataEntity data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataEntity {
        /**
         * count : 3
         * msg : 签到成功，已领取3蕉
         */

        private int count;
        private String msg;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
