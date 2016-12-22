package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;
import com.lxy.pink.ui.video.views.ACBannerView;

/**
 * Created by homelajiang on 2016/12/20 0020.
 */

public class ACBannerModel extends EpoxyModel<ACBannerView> {

    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;

    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_banner_model;
    }

    @Override
    public void bind(ACBannerView view) {
        view.setBanner(contentBean.getImage());
        if(acItemClickListener!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acItemClickListener.onItemClicked(v,contentBean);
                }
            });
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
