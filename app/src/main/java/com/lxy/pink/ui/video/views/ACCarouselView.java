package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACCarouselView extends RelativeLayout {

    @BindView(R.id.bga_banner)
    public BGABanner bgaBanner;

    public ACCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_video_carousel_view, this);
        ButterKnife.bind(this);
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
