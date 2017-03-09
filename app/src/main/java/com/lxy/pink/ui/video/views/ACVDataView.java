package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVDataView extends LinearLayout {
    @BindView(R.id.video_views)
    public LinearLayout mVideoViews;
    @BindView(R.id.video_danmakuSize)
    public LinearLayout mVideoDanmuKuSize;
    @BindView(R.id.video_goldBanana)
    public LinearLayout mVideoBanana;
    @BindView(R.id.video_stows)
    public LinearLayout mVideoStows;
    private ACVideoInfo.DataBean.VisitBean visit;

    public ACVDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.ac_video_info_data_view, this);
        ButterKnife.bind(this);
    }

    public void setVisit(ACVideoInfo.DataBean.VisitBean visit) {
        this.visit = visit;
        // TODO: 2017/3/9 0009 解释
    }
}
