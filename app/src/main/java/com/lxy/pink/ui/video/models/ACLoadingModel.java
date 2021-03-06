package com.lxy.pink.ui.video.models;

import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/3/9 0009.
 */

@EpoxyModelClass(layout = R.layout.ac_loading_model)
public abstract class ACLoadingModel extends EpoxyModelWithHolder<ACLoadingModel.ACLoadingViewHolder> {
//    @EpoxyAttribute
//    int paddTopButtom;

    @Override
    public void bind(ACLoadingViewHolder view) {
    }

    static class ACLoadingViewHolder extends EpoxyHolder {
        @BindView(R.id.loading_pb)
        ProgressBar mLoadingPb;
        @BindView(R.id.loading_text)
        TextView mLoadingText;
        @BindView(R.id.loading_layout)
        LinearLayout mLoadingLayout;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            int top = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, itemView.getContext().getResources().getDisplayMetrics());
            mLoadingLayout.setPadding(0, top, 0, top);
        }
    }

}
