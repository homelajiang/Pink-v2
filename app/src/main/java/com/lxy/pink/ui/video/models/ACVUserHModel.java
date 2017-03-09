package com.lxy.pink.ui.video.models;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.ui.video.views.ACVUserHView;
import com.lxy.pink.utils.FuzzyDateFormatter;

import java.util.Date;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_user_h_model)
public abstract class ACVUserHModel extends EpoxyModel<ACVUserHView> {
    @EpoxyAttribute
    String headIcon;
    @EpoxyAttribute
    String userName;
    @EpoxyAttribute
    long publishTime;
    @EpoxyAttribute
    int followed = -1;

    @Override
    public void bind(ACVUserHView view) {
        view.mUpName.setText(userName);
        view.mPublishTime.setText(FuzzyDateFormatter.getTimeAgo(view.getContext(), new Date(publishTime)));
        if (followed == 0) {
            view.mFollow.setEnabled(true);
            view.mFollow.setText(R.string.follow);
        } else if (followed == 1) {
            view.mFollow.setEnabled(true);
            view.mFollow.setText(R.string.unfollow);
        } else {
            view.mFollow.setEnabled(false);
            view.mFollow.setText(R.string.unfollow);
        }
        view.mUserIcon.setImageURI(headIcon);
    }
}
