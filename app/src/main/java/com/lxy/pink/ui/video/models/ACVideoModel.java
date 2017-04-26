package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;
import com.lxy.pink.utils.FrescoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/12/20 0020.
 */
@EpoxyModelClass(layout = R.layout.ac_video_h_view)
public abstract class ACVideoModel extends EpoxyModelWithHolder<ACVideoModel.ACVideoViewHolder> {

    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;

    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    public void bind(ACVideoViewHolder viewHolder) {
        viewHolder.setVideoCover(contentBean.getImage());
        viewHolder.setVideoTitle(contentBean.getTitle());
        if (contentBean.getVisit() != null) {
            viewHolder.setVideoDanmuCount(contentBean.getVisit().getDanmakuSize());
            viewHolder.setVideoPlayCount(contentBean.getVisit().getViews());
        } else {
            viewHolder.setVideoDanmuCount(0);
            viewHolder.setVideoPlayCount(0);
        }
        if (acItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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

    static class ACVideoViewHolder extends EpoxyHolder {
        @BindView(R.id.video_cover)
        SimpleDraweeView mVideoCover;
        @BindView(R.id.video_title)
        TextView mVideoTitle;
        @BindView(R.id.video_play_count)
        TextView mVideoPlayCount;
        @BindView(R.id.video_danmu_count)
        TextView mVideoDanmuCount;
        View itemView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public void setVideoCover(String url) {
            FrescoUtils.setImage(url, mVideoCover);
        }

        public void setVideoTitle(String title) {
            mVideoTitle.setText(title);
        }

        public void setVideoPlayCount(int count) {
            mVideoPlayCount.setText(String.valueOf(count));
        }

        public void setVideoDanmuCount(int count) {
            mVideoDanmuCount.setText(String.valueOf(count));
        }
    }
}
