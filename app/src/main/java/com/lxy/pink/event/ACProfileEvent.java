package com.lxy.pink.event;

import com.lxy.pink.data.model.acfun.ACProfile;

/**
 * Created by homelajiang on 2017/2/23 0023.
 */

public class ACProfileEvent {
    ACProfile acProfile;

    public ACProfileEvent() {
    }

    public ACProfileEvent(ACProfile acProfile) {
        this.acProfile = acProfile;
    }

    public ACProfile getAcProfile() {
        return acProfile;
    }
}
