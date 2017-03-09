package com.lxy.pink.ui.video.models;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;

/**
 * Created by homelajiang on 2017/3/9 0009.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_introduction_model)
public abstract class ACVIntroModel extends EpoxyModel<TextView> {
    @EpoxyAttribute
    String introduction;

    @Override
    public void bind(TextView view) {
        view.setText(introduction);
    }
}
