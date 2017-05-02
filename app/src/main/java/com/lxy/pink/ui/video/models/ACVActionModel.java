package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.LinearLayout;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_action)
public abstract class ACVActionModel extends EpoxyModelWithHolder<ACVActionModel.ACVActionViewHolder> {

    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    public void bind(ACVActionViewHolder viewHolder) {
        super.bind(viewHolder);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static class ACVActionViewHolder extends EpoxyHolder {

        @BindView(R.id.layout_share)
        LinearLayout mLayoutShare;
        @BindView(R.id.layout_feed)
        LinearLayout mLayoutFeed;
        @BindView(R.id.layout_mark)
        LinearLayout mLayoutMark;
        @BindView(R.id.layout_cache)
        LinearLayout mLayoutCache;
        View itemView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
