package com.lxy.pink.ui.video.models;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.video.video.ACVideoUserRefAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/3/9 0009.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_recyclerview_bg_model)
public abstract class ACVUserListModel extends EpoxyModelWithHolder<ACVUserListModel.UserRecyclerViewHolder> {
    @EpoxyAttribute
    String avatar;
    @EpoxyAttribute
    int userId;
    @EpoxyAttribute
    String name;
    @EpoxyAttribute
    List<ACVideoSearchLike.DataEntity.PageEntity.ListEntity> recommendList;
    private ACVideoUserRefAdapter adapter;

    @Override
    public void bind(UserRecyclerViewHolder view) {
        view.recyclerView.setLayoutManager(new LinearLayoutManager(view.recyclerView.getContext()
                , LinearLayoutManager.HORIZONTAL, false));
        view.recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (recommendList == null) {
            adapter = new ACVideoUserRefAdapter(avatar, userId, name);
            view.recyclerView.setAdapter(adapter);
        } else {
            adapter.setRecommendList(recommendList);
        }
    }


    static class UserRecyclerViewHolder extends EpoxyHolder {
        @BindView(R.id.simpleDraweeViewBg)
        SimpleDraweeView simpleDraweeViewBg;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
        }

    }
}
