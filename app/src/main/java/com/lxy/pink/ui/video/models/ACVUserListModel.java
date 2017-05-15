package com.lxy.pink.ui.video.models;

import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.Injection;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.ui.video.detail.ACVideoUserRefAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Created by homelajiang on 2017/3/9 0009.
 */

@EpoxyModelClass(layout = R.layout.recyclerview_with_bg)
public abstract class ACVUserListModel extends EpoxyModelWithHolder<ACVUserListModel.UserRecyclerViewHolder> {
    @EpoxyAttribute
    String avatar;
    @EpoxyAttribute
    int userId;
    @EpoxyAttribute
    String name;
    @EpoxyAttribute
    List<ACUserContribute.DataEntity.PageEntity.ListEntity> upContribuctionList;
    private ACVideoUserRefAdapter adapter;

    @Override
    public void bind(UserRecyclerViewHolder view) {
        adapter = new ACVideoUserRefAdapter(avatar, userId, name, upContribuctionList);
        view.recyclerView.setAdapter(adapter);
    }


    static class UserRecyclerViewHolder extends EpoxyHolder {
        @BindView(R.id.simpleDraweeViewBg)
        SimpleDraweeView simpleDraweeViewBg;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()
                    , LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    DisplayMetrics metrics = Injection.provideContext().getResources().getDisplayMetrics();
                    int size1 = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, 25f, metrics);
                    int size2 = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, 12f, metrics);
                    outRect.set(size2, size1, size2, size1);
                }
            });
        }
    }
}
