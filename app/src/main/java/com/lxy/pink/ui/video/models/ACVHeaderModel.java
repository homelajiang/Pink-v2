package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_header_model)
public abstract class ACVHeaderModel extends EpoxyModel<TextView> {

    @EpoxyAttribute
    String title;
    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    public void bind(TextView view) {
        view.setText(title);
        view.setOnClickListener(clickListener);
    }
}
