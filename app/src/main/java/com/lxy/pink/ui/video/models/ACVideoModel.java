package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;
import com.lxy.pink.ui.video.views.ACVideoView;

/**
 * Created by homelajiang on 2016/12/20 0020.
 */

public class ACVideoModel extends EpoxyModel<ACVideoView> {

    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;

    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_video_h_model;
    }

    @Override
    public void bind(ACVideoView view) {
        view.setVideoCover(contentBean.getImage());
        view.setVideoTitle(contentBean.getTitle());
        if(contentBean.getVisit()!=null){
            view.setVideoDanmuCount(contentBean.getVisit().getDanmakuSize());
            view.setVideoPlayCount(contentBean.getVisit().getViews());
        }else {
            view.setVideoDanmuCount(0);
            view.setVideoPlayCount(0);
        }
        if (acItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acItemClickListener.onItemClicked(v, contentBean);
                }
            });
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount / 2;
    }
}
