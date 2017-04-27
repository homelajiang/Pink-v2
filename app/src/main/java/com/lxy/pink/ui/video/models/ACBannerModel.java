package com.lxy.pink.ui.video.models;

import android.view.View;

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
@EpoxyModelClass(layout = R.layout.ac_banner_view)
public abstract class ACBannerModel extends EpoxyModelWithHolder<ACBannerModel.ACBannerViewHolder> {

    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;

    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    public void bind(ACBannerViewHolder viewHolder) {
        viewHolder.setBanner(contentBean.getImage());
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

    static class ACBannerViewHolder extends EpoxyHolder {
        @BindView(R.id.banner)
        SimpleDraweeView mBanner;
        View itemView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public void setBanner(String url) {
            FrescoUtils.setImage(url, mBanner);
        }

    }
}
