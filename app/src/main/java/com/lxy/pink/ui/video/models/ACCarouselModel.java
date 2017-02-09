package com.lxy.pink.ui.video.models;

import android.util.Log;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;
import com.lxy.pink.ui.video.views.ACCarouselView;
import com.orhanobut.logger.Logger;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACCarouselModel extends EpoxyModel<ACCarouselView> {
    @EpoxyAttribute
    ACRecommend.DataBean dataBean;

    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_video_carousel_model;
    }

    @Override
    public void bind(ACCarouselView view) {
        Log.e("bind_test","bindView");
        view.setBgaBanner(dataBean.getContents());
        if (acItemClickListener != null) {
            view.bgaBanner.setDelegate(new BGABanner.Delegate() {
                @Override
                public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                    acItemClickListener.onItemClicked(itemView, dataBean.getContents().get(position));
                }
            });
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void unbind(ACCarouselView view) {
        super.unbind(view);
        Log.e("bind_test","unBindView");
    }
}