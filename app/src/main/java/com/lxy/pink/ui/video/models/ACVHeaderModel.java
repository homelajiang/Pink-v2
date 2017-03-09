package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACVHeaderView;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_header_model)
public abstract class ACVHeaderModel extends EpoxyModel<ACVHeaderView> {

    @EpoxyAttribute
    String title;
    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    public void bind(ACVHeaderView view) {
        view.mTitle.setText(title);
        view.mSubTitle.setOnClickListener(clickListener);
    }
}
