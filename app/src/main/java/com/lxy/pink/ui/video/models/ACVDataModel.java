package com.lxy.pink.ui.video.models;

import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_data_view)
public abstract class ACVDataModel extends EpoxyModelWithHolder<ACVDataModel.ACVDataHolder> {
    @EpoxyAttribute
    ACVideoInfo.DataBean.VisitBean visitBean;
    @EpoxyAttribute
    int marked = -1;

    @Override
    public void bind(ACVDataHolder view) {
        view.mViewCount.setText(String.valueOf(visitBean.getViews()));
        view.mDanmuCount.setText(String.valueOf(visitBean.getDanmakuSize()));
        view.mBananaCount.setText(String.valueOf(visitBean.getGoldBanana()));
        view.mMarkCount.setText(String.valueOf(visitBean.getStows()));

        if (marked == 0) {
            view.mMarkRadioButton.setEnabled(true);
            view.mMarkRadioButton.setChecked(false);
        } else if (marked == 1) {
            view.mMarkRadioButton.setEnabled(true);
            view.mMarkRadioButton.setChecked(true);
        } else {
            view.mMarkRadioButton.setEnabled(false);
            view.mMarkRadioButton.setChecked(false);
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }


    static class ACVDataHolder extends EpoxyHolder {
        @BindView(R.id.view_count)
        public TextView mViewCount;
        @BindView(R.id.view_count_unit)
        public TextView mViewCountUnit;
        @BindView(R.id.danmu_count)
        public TextView mDanmuCount;
        @BindView(R.id.danmu_count_unit)
        public TextView mDanmuCountUnit;
        @BindView(R.id.send_banana)
        public AppCompatImageButton mSendBanana;
        @BindView(R.id.banana_count)
        public TextView mBananaCount;
        @BindView(R.id.mark_radio_button)
        public AppCompatRadioButton mMarkRadioButton;
        @BindView(R.id.mark_count)
        public TextView mMarkCount;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
