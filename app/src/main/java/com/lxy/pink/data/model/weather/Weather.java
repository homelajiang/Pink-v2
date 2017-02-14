package com.lxy.pink.data.model.weather;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * Created by yuan on 2016/10/9.
 */

@Entity
public class Weather {
    @Id
    private long _id = 0;
    private String id;
    private String name;
    private String country;
    private String path;
    private String timezone;
    private String timzoneOffset;
    private String temperature;
    private String code;
    private String text;
    private Date lastUpdate = new Date();

    @Generated(hash = 1822033055)
    public Weather(long _id, String id, String name, String country, String path, String timezone, String timzoneOffset, String temperature, String code, String text,
            Date lastUpdate) {
        this._id = _id;
        this.id = id;
        this.name = name;
        this.country = country;
        this.path = path;
        this.timezone = timezone;
        this.timzoneOffset = timzoneOffset;
        this.temperature = temperature;
        this.code = code;
        this.text = text;
        this.lastUpdate = lastUpdate;
    }

    @Generated(hash = 556711069)
    public Weather() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimzoneOffset() {
        return timzoneOffset;
    }

    public void setTimzoneOffset(String timzoneOffset) {
        this.timzoneOffset = timzoneOffset;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long get_id() {
        return this._id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}
