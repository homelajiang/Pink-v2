package com.lxy.pink.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by homelajiang on 2016/11/23 0023.
 */

@Entity
public class TempModel {
    public static final int TYPE_WEATHER = 2;
    public static final int TYPE_FORECAST = 3;

    private int type;
    private long updateTime = System.currentTimeMillis();
    private String raw;

    @Generated(hash = 1336560835)
    public TempModel(int type, long updateTime, String raw) {
        this.type = type;
        this.updateTime = updateTime;
        this.raw = raw;
    }
    @Generated(hash = 1236443483)
    public TempModel() {
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
    public String getRaw() {
        return this.raw;
    }
    public void setRaw(String raw) {
        this.raw = raw;
    }

}
