package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.views.ACFunView;

/**
 * Created by homelajiang on 2016/12/20 0020.
 */

public class ACFunModel extends EpoxyModel<ACFunView> {

    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;
    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_fun_v_model;
    }

    @Override
    public void bind(ACFunView view) {
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount / 3;
    }
}
