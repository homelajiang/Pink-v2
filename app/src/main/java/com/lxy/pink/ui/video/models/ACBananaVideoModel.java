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
 * Created by yuan on 2016/12/20.
 */
@EpoxyModelClass(layout = R.layout.ac_banana_video_view)
public abstract class ACBananaVideoModel extends EpoxyModelWithHolder<ACBananaVideoModel.ACBananaVideoViewHolder> {
    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;
    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    public void bind(ACBananaVideoViewHolder viewHolder) {
        viewHolder.setVideoCover(contentBean.getImage());
        viewHolder.setVideoTitle(contentBean.getTitle());
        viewHolder.setUpName(contentBean.getOwner().getName());
        viewHolder.setVideoBanana(contentBean.getVisit().getGoldBanana());
        viewHolder.setData(contentBean);
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
        return totalSpanCount;
    }

    static class ACBananaVideoViewHolder extends EpoxyHolder {
        @BindView(R.id.video_cover)
        SimpleDraweeView videoCover;
        @BindView(R.id.videoTitle)
        TextView videoTitle;
        @BindView(R.id.videoBanana)
        TextView videoBanana;
        @BindView(R.id.upName)
        TextView upName;
        @BindView(R.id.viewCount)
        TextView mViewCount;
        View itemView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public void setData(ACRecommend.DataBean.ContentsBean contentBean) {
            FrescoUtils.setImage(contentBean.getUrl(), videoCover);
            videoTitle.setText(String.valueOf(contentBean.getTitle()));
            upName.setText(String.valueOf(contentBean.getOwner().getName()));
            videoBanana.setText(String.valueOf(contentBean.getVisit().getGoldBanana()));
            mViewCount.setText(String.valueOf(contentBean.getVisit().getViews()));
        }

        public void setVideoCover(String url) {
            FrescoUtils.setImage(url, videoCover);
        }

        public void setVideoTitle(String title) {
            this.videoTitle.setText(title);
        }

        public void setUpName(String upName) {
            this.upName.setText(upName);
        }

        public void setVideoBanana(int videoBanana) {
            this.videoBanana.setText(String.valueOf(videoBanana));
        }

    }
}
