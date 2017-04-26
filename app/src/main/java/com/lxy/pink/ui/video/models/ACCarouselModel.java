package com.lxy.pink.ui.video.models;

import android.net.Uri;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by yuan on 2016/12/19.
 */
@EpoxyModelClass(layout = R.layout.ac_video_carousel_view)
public abstract class ACCarouselModel extends EpoxyModelWithHolder<ACCarouselModel.ACCarouselViewHolder> {
    @EpoxyAttribute
    ACRecommend.DataBean dataBean;

    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    public void bind(ACCarouselViewHolder viewHolder) {
        viewHolder.setBgaBanner(dataBean.getContents());
        if (acItemClickListener != null) {
            viewHolder.bgaBanner.setDelegate(new BGABanner.Delegate() {
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
    public void unbind(ACCarouselViewHolder holder) {
        super.unbind(holder);
    }

    static class ACCarouselViewHolder extends EpoxyHolder {
        @BindView(R.id.bga_banner)
        public BGABanner bgaBanner;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            bgaBanner.setAdapter(new BGABanner.Adapter<SimpleDraweeView, ACRecommend.DataBean.ContentsBean>() {
                @Override
                public void fillBannerItem(BGABanner banner, SimpleDraweeView itemView, ACRecommend.DataBean.ContentsBean model, int position) {
                    itemView.setImageURI(Uri.parse(model.getImage()));
                }
            });
        }
        public void setBgaBanner(List<ACRecommend.DataBean.ContentsBean> contentsBeanList) {
            bgaBanner.setData(R.layout.video_banner_simpledraweeview, contentsBeanList, null);
        }
    }
}
