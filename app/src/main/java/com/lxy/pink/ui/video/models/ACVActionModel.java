package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACVActionView;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_action_model)
public abstract class ACVActionModel extends EpoxyModel<ACVActionView> {

    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    public void bind(ACVActionView view) {
        super.bind(view);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
