package com.lxy.pink.ui.video.models;

import android.graphics.Rect;
import android.support.annotation.MenuRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.lxy.pink.Injection;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/5/16 0016.
 */
@EpoxyModelClass(layout = R.layout.recyclerview)
public abstract class ACVPartModel extends EpoxyModelWithHolder<ACVPartModel.ACVPartViewHolder> {

    @EpoxyAttribute
    List<ACVideoInfo.DataBean.VideosBean> videoList;

    @Override
    public void bind(ACVPartViewHolder holder) {
        holder.setData(videoList);
    }

    static class ACVPartViewHolder extends EpoxyHolder {
        @BindView(R.id.recyclerView)
        RecyclerView mRecyclerView;

        @Override
        protected void bindView(View itemView) {
            DisplayMetrics metrics = Injection.provideContext().getResources().getDisplayMetrics();
            final int decoration = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, metrics);

            ButterKnife.bind(this, itemView);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setPadding(2*decoration,decoration/2,2*decoration,decoration);
            mRecyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    outRect.set(decoration, decoration, decoration, decoration);
                }
            });
        }

        public void setData(List<ACVideoInfo.DataBean.VideosBean> videoList) {
            if (videoList == null)
                return;
            mRecyclerView.setAdapter(new ACVPartAdapter(videoList));
        }
    }

    private static class ACVPartAdapter extends EpoxyAdapter {
        ACVPartAdapter(List<ACVideoInfo.DataBean.VideosBean> videoList) {
            boolean isFirst = true;
            for (ACVideoInfo.DataBean.VideosBean video : videoList) {
                ACVPartItemModel partItemModel = new ACVPartItemModel_()
                        .video(video)
                        .selected(isFirst);
                addModel(partItemModel);
                isFirst = false;
            }
        }
    }

}
