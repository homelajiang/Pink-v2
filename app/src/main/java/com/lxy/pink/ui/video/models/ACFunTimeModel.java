package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.ImageView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/12/20.
 */
@EpoxyModelClass(layout = R.layout.ac_fun_time_view)
public abstract class ACFunTimeModel extends EpoxyModelWithHolder<ACFunTimeModel.ACFunTimeViewHolder> {
    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    public void bind(ACFunTimeViewHolder view) {
        if (clickListener != null) {
            view.funTimeLeft.setOnClickListener(clickListener);
            view.funTimeRight.setOnClickListener(clickListener);
        }
    }
    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static class ACFunTimeViewHolder extends EpoxyHolder {

        @BindView(R.id.fun_time_left)
        public ImageView funTimeLeft;
        @BindView(R.id.fun_time_right)
        public ImageView funTimeRight;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
