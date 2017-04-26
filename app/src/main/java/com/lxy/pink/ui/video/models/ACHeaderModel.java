package com.lxy.pink.ui.video.models;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;
import com.lxy.pink.utils.FrescoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/12/20.
 */
@EpoxyModelClass(layout = R.layout.ac_header_view)
public abstract class ACHeaderModel extends EpoxyModelWithHolder<ACHeaderModel.ACHeaderViewHolder> {
    @EpoxyAttribute
    ACRecommend.DataBean dataBean;
    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean> acItemClickListener;

    @Override
    public void bind(final ACHeaderViewHolder view) {
        view.setTitle(dataBean.getName());
        view.setIcon(dataBean.getImage());
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static class ACHeaderViewHolder extends EpoxyHolder {
        @BindView(R.id.icon)
        SimpleDraweeView icon;
        @BindView(R.id.title)
        TextView title;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
        }

        public void setIcon(String url) {
            FrescoUtils.setImage(url, icon);
        }

        public void setTitle(String title) {
            if (!TextUtils.isEmpty(title))
                this.title.setText(title);
        }
    }
}
