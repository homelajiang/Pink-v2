package com.lxy.pink.event;

import com.lxy.pink.data.model.auth.Auth;

/**
 * Created by homelajiang on 2016/10/19 0019.
 */

public class AuthCreateEvent {
    public Auth auth;

    public AuthCreateEvent(Auth auth) {
        this.auth = auth;
    }
}
