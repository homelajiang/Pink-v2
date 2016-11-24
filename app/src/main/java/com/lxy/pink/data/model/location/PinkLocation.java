package com.lxy.pink.data.model.location;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuan on 2016/10/20.
 */

@Entity
public class PinkLocation {
    private int locType;
    private long time;
    private double latitude;
    private double longitude;
    private double altitude;
    private float speed;
    private float radius;
    private float direction;
    private long saveTime = System.currentTimeMillis();


    @Generated(hash = 1671819452)
    public PinkLocation(int locType, long time, double latitude, double longitude,
            double altitude, float speed, float radius, float direction,
            long saveTime) {
        this.locType = locType;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.radius = radius;
        this.direction = direction;
        this.saveTime = saveTime;
    }

    @Generated(hash = 838624561)
    public PinkLocation() {
    }


    public int getLocType() {
        return locType;
    }

    public void setLocType(int locType) {
        this.locType = locType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }
}
