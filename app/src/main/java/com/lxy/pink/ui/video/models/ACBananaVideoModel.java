package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;
import com.lxy.pink.ui.video.views.ACBananaVideoView;

/**
 * Created by yuan on 2016/12/20.
 */

public class ACBananaVideoModel extends EpoxyModel<ACBananaVideoView> {
    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;

    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_banana_video_model;
    }

    @Override
    public void bind(ACBananaVideoView view) {
        view.setVideoCover(contentBean.getImage());
        view.setVideoTitle(contentBean.getTitle());
        view.setUpName(contentBean.getOwner().getName());
        view.setVideoBanana(contentBean.getVisit().getGoldBanana());
        if(acItemClickListener !=null){
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
