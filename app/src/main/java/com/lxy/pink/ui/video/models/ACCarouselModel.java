package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.views.ACCarouselView;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACCarouselModel extends EpoxyModel<ACCarouselView>{
    @EpoxyAttribute
    ACRecommend.DataBean dataBean;

    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_video_carousel_model;
    }

    @Override
    public void bind(ACCarouselView view) {
        super.bind(view);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
