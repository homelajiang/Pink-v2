package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_v_lite_model)
public abstract class ACVLiteModel extends EpoxyModelWithHolder<ACVLiteModel.ACVLiteViewHolder> {
    @EpoxyAttribute
    String coverUrl;
    @EpoxyAttribute
    String name;
    @EpoxyAttribute
    int contentId;

    @Override
    public void bind(ACVLiteViewHolder holder) {
        holder.mVideoTitle.setText(String.valueOf(name));
        holder.mVideoCover.setImageURI(coverUrl);
    }

    static class ACVLiteViewHolder extends EpoxyHolder {
        @BindView(R.id.video_cover)
        SimpleDraweeView mVideoCover;
        @BindView(R.id.video_title)
        TextView mVideoTitle;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

}
