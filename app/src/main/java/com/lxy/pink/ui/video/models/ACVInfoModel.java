package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/5/2 0002.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_view)
public abstract class ACVInfoModel extends EpoxyModelWithHolder<ACVInfoModel.ACVInfoHolderView> {
    @EpoxyAttribute
    String title;
    @EpoxyAttribute
    String videoInfo;
    @EpoxyAttribute
    String videoPlayCount;
    @EpoxyAttribute
    String videoDanmuCount;
    @EpoxyAttribute
    View.OnClickListener onClickListener;

    @Override
    public void bind(ACVInfoHolderView holder) {
        super.bind(holder);
        holder.mTitle.setText(title);
        holder.mVideoPlayCount.setText(videoPlayCount);
        holder.mVideoDanmuCount.setText(videoDanmuCount);
        holder.mVideoInfo.setText(videoInfo);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static class ACVInfoHolderView extends EpoxyHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.video_play_count)
        TextView mVideoPlayCount;
        @BindView(R.id.video_danmu_count)
        TextView mVideoDanmuCount;
        @BindView(R.id.video_info)
        TextView mVideoInfo;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
