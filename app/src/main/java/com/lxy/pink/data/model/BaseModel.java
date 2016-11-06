package com.lxy.pink.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */
@Entity
public class BaseModel {
    private int code;
    private String msg;

    @Generated(hash = 1005282232)
    public BaseModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Generated(hash = 851705445)
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
