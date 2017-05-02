package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.utils.FuzzyDateFormatter;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_user_h_view)
public abstract class ACVUserHModel extends EpoxyModelWithHolder<ACVUserHModel.ACVUserHViewHolder> {
    @EpoxyAttribute
    String headIcon;
    @EpoxyAttribute
    String userName;
    @EpoxyAttribute
    long publishTime;
    @EpoxyAttribute
    int followed = -1;

    @Override
    public void bind(ACVUserHViewHolder viewHolder) {
        viewHolder.mUpName.setText(userName);
        viewHolder.mPublishTime.setText(FuzzyDateFormatter.getTimeAgo(viewHolder.itemView.getContext()
                , new Date(publishTime)));
        viewHolder.mUserIcon.setImageURI(headIcon);
        if (followed == 0) {
            viewHolder.mFollow.setEnabled(true);
            viewHolder.mFollow.setText(R.string.follow);
        } else if (followed == 1) {
            viewHolder.mFollow.setEnabled(true);
            viewHolder.mFollow.setText(R.string.unfollow);
        } else {
            viewHolder.mFollow.setEnabled(false);
            viewHolder.mFollow.setText(R.string.unfollow);
        }
    }

    static class ACVUserHViewHolder extends EpoxyHolder {

        @BindView(R.id.user_icon)
        public SimpleDraweeView mUserIcon;
        @BindView(R.id.up_name)
        public TextView mUpName;
        @BindView(R.id.publish_time)
        public TextView mPublishTime;
        @BindView(R.id.follow)
        public RadioButton mFollow;
        View itemView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

    }
}
