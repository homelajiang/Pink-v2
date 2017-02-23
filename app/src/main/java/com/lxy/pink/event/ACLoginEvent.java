package com.lxy.pink.event;

import com.lxy.pink.data.model.acfun.ACAuth;
import com.lxy.pink.data.model.acfun.ACProfile;

/**
 * Created by homelajiang on 2017/2/23 0023.
 */

public class ACLoginEvent {
    ACAuth acAuth;
    ACProfile acProfile;
    boolean sign = true;

    public ACLoginEvent() {
    }

    public boolean isSign() {
        return sign;
    }

    public ACLoginEvent(boolean sign) {
        this.sign = sign;
    }

    public ACLoginEvent(ACAuth acAuth) {
        this.acAuth = acAuth;
    }

    public ACLoginEvent(ACProfile acProfile) {
        this.acProfile = acProfile;
    }

    public ACLoginEvent(ACAuth acAuth, ACProfile acProfile) {
        this.acAuth = acAuth;
        this.acProfile = acProfile;
    }

    public ACAuth getAcAuth() {
        return acAuth;
    }

    public ACProfile getAcProfile() {
        return acProfile;
    }
}
