package com.lxy.pink.data.model;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */
public class BaseModel {
    private int code;
    private String msg;

    public BaseModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseModel() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
