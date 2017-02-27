package com.lxy.pink.event;

import com.lxy.pink.data.model.acfun.ACProfile;

/**
 * Created by homelajiang on 2017/2/23 0023.
 */

public class ACLoginEvent {
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

    public ACLoginEvent(ACProfile acProfile) {
        this.acProfile = acProfile;
    }

    public ACProfile getAcProfile() {
        return acProfile;
    }
}
