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
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
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
    ACVideoSearchLike.DataEntity.PageEntity.ListEntity searchEntry;
    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    public void bind(ACBananaVideoViewHolder viewHolder) {
        if (contentBean != null) {
            viewHolder.setData(contentBean);
            if (acItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        acItemClickListener.onItemClicked(v, contentBean);
                    }
                });
            }
        } else if (searchEntry != null) {
            viewHolder.setData(searchEntry);
        } else {

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
            FrescoUtils.setImage(contentBean.getImage(), videoCover);
            videoTitle.setText(String.valueOf(contentBean.getTitle()));
            upName.setText(String.valueOf(contentBean.getOwner().getName()));
            videoBanana.setText(String.valueOf(contentBean.getVisit().getGoldBanana()));
            mViewCount.setText(String.valueOf(contentBean.getVisit().getViews()));
        }

        public void setData(ACVideoSearchLike.DataEntity.PageEntity.ListEntity searchEntry) {
            FrescoUtils.setImage(searchEntry.getTitleImg(), videoCover);
            videoTitle.setText(String.valueOf(searchEntry.getTitle()));
            upName.setText(String.valueOf(searchEntry.getUsername()));
            videoBanana.setText(String.valueOf(searchEntry.getComments()));
            mViewCount.setText(String.valueOf(searchEntry.getViews()));
        }
    }
}
