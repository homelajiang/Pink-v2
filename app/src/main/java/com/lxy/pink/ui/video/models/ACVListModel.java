package com.lxy.pink.ui.video.models;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */
@EpoxyModelClass(layout = R.layout.ac_video_info_recyclerview_model)
public abstract class ACVListModel extends EpoxyModel<RecyclerView> {
    @EpoxyAttribute
    RecyclerView.Adapter adapter;

    @Override
    public void bind(RecyclerView view) {
        if(adapter!=null){

        }
    }
}
