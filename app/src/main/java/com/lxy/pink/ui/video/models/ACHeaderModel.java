package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;
import com.lxy.pink.ui.video.views.ACHeaderView;

/**
 * Created by yuan on 2016/12/20.
 */

public class ACHeaderModel extends EpoxyModel<ACHeaderView> {
    @EpoxyAttribute
    ACRecommend.DataBean dataBean;
    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean> acItemClickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_header_model;
    }

    @Override
    public void bind(final ACHeaderView view) {
        view.setTitle(dataBean.getName());
        view.setIcon(dataBean.getImage());

        if (acItemClickListener != null) {
            view.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acItemClickListener.onItemClicked(view, dataBean);
                }
            });
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
