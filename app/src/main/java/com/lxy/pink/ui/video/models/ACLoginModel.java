package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACUser;
import com.lxy.pink.ui.video.views.ACLoginView;

import java.util.List;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACLoginModel extends EpoxyModel<ACLoginView>{

    @EpoxyAttribute ACUser acUser;
    @EpoxyAttribute View.OnClickListener clickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_login_model;
    }

    @Override
    public void bind(ACLoginView view) {
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
