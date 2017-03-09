package com.lxy.pink.ui.video.models;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACVLiteView;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_v_lite_model)
public abstract class ACVLiteModel extends EpoxyModel<ACVLiteView> {
    @EpoxyAttribute
    String coverUrl;
    @EpoxyAttribute
    String name;
    @EpoxyAttribute
    String contentId;
}
