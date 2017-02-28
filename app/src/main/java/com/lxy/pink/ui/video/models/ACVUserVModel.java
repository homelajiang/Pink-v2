package com.lxy.pink.ui.video.models;

import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACVUserVView;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVUserVModel extends EpoxyModel<ACVUserVView> {
    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_video_info_user_v_model;
    }
}
