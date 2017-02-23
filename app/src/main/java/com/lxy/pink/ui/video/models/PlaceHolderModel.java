package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.SimpleEpoxyModel;
import com.lxy.pink.R;

/**
 * Created by homelajiang on 2017/2/23 0023.
 */

public class PlaceHolderModel extends SimpleEpoxyModel {
    public PlaceHolderModel() {
        super(R.layout.epoxy_placeholder);
    }

    @Override
    public void bind(View view) {
        super.bind(view);
    }
}
