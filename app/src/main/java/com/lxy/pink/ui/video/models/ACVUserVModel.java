package com.lxy.pink.ui.video.models;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACVUserVView;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_user_v_model)
public abstract class ACVUserVModel extends EpoxyModel<ACVUserVView> {
    @EpoxyAttribute
    String avatar;
    @EpoxyAttribute
    int userId;
    @EpoxyAttribute
    String name;

    @Override
    public void bind(ACVUserVView view) {
        view.mUserIcon.setImageURI(this.avatar);
        view.mUserName.setText(this.name);
    }
}
