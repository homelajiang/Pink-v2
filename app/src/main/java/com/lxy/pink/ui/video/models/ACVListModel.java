package com.lxy.pink.ui.video.models;

import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.Injection;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.video.video.ACVideoRecommendAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_recyclerview)
public abstract class ACVListModel extends EpoxyModelWithHolder<ACVListModel.VLiteRecyclerViewHolder> {

    @EpoxyAttribute
    List<ACVideoSearchLike.DataEntity.PageEntity.ListEntity> recommendList;
    @EpoxyAttribute
    ACVideoRecommendAdapter adapter;

    @Override
    public void bind(VLiteRecyclerViewHolder view) {
        adapter = new ACVideoRecommendAdapter(recommendList);
        view.recyclerView.setAdapter(adapter);
    }

    static class VLiteRecyclerViewHolder extends EpoxyHolder {
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()
                    , LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    DisplayMetrics metrics = Injection.provideContext().getResources().getDisplayMetrics();
                    int size2 = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, 14f, metrics);
                    outRect.set(size2, size2 / 2, size2, size2 / 2);
                }
            });
        }
    }
}
