package com.lxy.pink.event;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACSignEvent {
    public ACSignEvent(boolean sign) {
        this.sign = sign;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    boolean sign;

}
