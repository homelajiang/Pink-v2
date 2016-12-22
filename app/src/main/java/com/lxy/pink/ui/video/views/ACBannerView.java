package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.utils.FrescoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/12/20 0020.
 */

public class ACBannerView extends CardView {
    @BindView(R.id.banner)
    SimpleDraweeView mBanner;

    public ACBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_banner_view, this);
        ButterKnife.bind(this);
    }

    public void setBanner(String url) {
        FrescoUtils.setpImage(url,mBanner);
    }
    // TODO: 2016/12/20 0020  set methods
}
