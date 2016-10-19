package com.lxy.pink.event;

import com.lxy.pink.data.model.auth.Profile;

/**
 * Created by homelajiang on 2016/10/19 0019.
 */

public class ProfileUpdateEvent {
    public Profile profile;

    public ProfileUpdateEvent(Profile profile) {
        this.profile = profile;
    }

}
