package com.lxy.pink.ui.home.impl;

import com.lxy.pink.data.model.location.PinkLocation;

/**
 * Created by homelajiang on 2017/1/12 0012.
 */

public interface PinkLocationCallback {
    void locationStart();

    void locationLoaded(PinkLocation pinkLocation);
}
