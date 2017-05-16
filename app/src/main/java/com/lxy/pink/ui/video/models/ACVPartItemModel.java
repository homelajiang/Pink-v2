package com.lxy.pink.ui.video.models;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoInfo;

/**
 * Created by homelajiang on 2017/5/16 0016.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_part_item)
public abstract class ACVPartItemModel extends EpoxyModel<TextView> {
    @EpoxyAttribute
    boolean selected;
    @EpoxyAttribute
    ACVideoInfo.DataBean.VideosBean video;

    @Override
    public void bind(TextView view) {
        if (selected) {
            view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bg_part_selected));
            view.setText(video.getTitle());
            view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.pink));
        } else {
            view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bg_part_selector));
            view.setText(video.getTitle());
            view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
        }
    }
}
