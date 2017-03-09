package com.lxy.pink.ui.video.models;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_title_model)
public abstract class ACVTitleModel extends EpoxyModel<TextView> {

    @EpoxyAttribute
    String title;

    @Override
    public void bind(TextView view) {
        view.setText(title);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
