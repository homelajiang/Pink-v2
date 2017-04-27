package com.lxy.pink.ui.video.models;

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
 * Created by homelajiang on 2016/12/20 0020.
 */
@EpoxyModelClass(layout = R.layout.ac_fun_v_view)
public abstract class ACFunModel extends EpoxyModelWithHolder<ACFunModel.ACFunViewHolder> {

    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;
    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    public void bind(ACFunViewHolder viewHolder) {
        viewHolder.setFunCover(contentBean.getImage());
        viewHolder.setFunTitle(contentBean.getTitle());
        viewHolder.setFunInfo(contentBean.getLatestBangumiVideo().getTitle());

        if(acItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acItemClickListener.onItemClicked(v,contentBean);
                }
            });
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount / 3;
    }

    static class ACFunViewHolder extends EpoxyHolder{
        @BindView(R.id.fun_cover)
        SimpleDraweeView mFunCover;
        @BindView(R.id.fun_title)
        TextView mFunTitle;
        @BindView(R.id.fun_info)
        TextView mFunInfo;
        View itemView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this,itemView);
            this.itemView = itemView;
        }

        public void setFunCover(String url) {
            FrescoUtils.setImage(url, mFunCover);
        }

        public void setFunTitle(String title) {
            this.mFunTitle.setText(title);
        }

        public void setFunInfo(String info) {
            this.mFunInfo.setText(info);
        }
    }
}
