package com.lxy.pink.data.model.acfun;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACBaseModel {

    /**
     * code : 200
     * data : true
     * message : OK
     */

    private int code;
    private String data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
