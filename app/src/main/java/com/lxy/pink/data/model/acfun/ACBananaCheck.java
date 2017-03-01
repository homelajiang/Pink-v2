package com.lxy.pink.data.model.acfun;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACBananaCheck {

    /**
     * code : 200
     * data : {"total":5,"remain":5}
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
         * total : 5
         * remain : 5
         */

        private int total;
        private int remain;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRemain() {
            return remain;
        }

        public void setRemain(int remain) {
            this.remain = remain;
        }
    }
}
