package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACVUserVView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_user_v_view)
public abstract class ACVUserVModel extends EpoxyModelWithHolder<ACVUserVModel.ACVUserVViewHolder> {
    @EpoxyAttribute
    String avatar;
    @EpoxyAttribute
    int userId;
    @EpoxyAttribute
    String name;

    @Override
    public void bind(ACVUserVViewHolder viewHolder) {
        viewHolder.mUserIcon.setImageURI(this.avatar);
        viewHolder.mUserName.setText(this.name);
    }

    static class ACVUserVViewHolder extends EpoxyHolder {
        @BindView(R.id.user_icon)
        public SimpleDraweeView mUserIcon;
        @BindView(R.id.user_name)
        public TextView mUserName;
        @BindView(R.id.more)
        public TextView mMore;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
        }

    }
}
