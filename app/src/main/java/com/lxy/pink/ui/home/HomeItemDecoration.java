package com.lxy.pink.ui.home;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class HomeItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 20;
        outRect.right = 20;
        outRect.bottom = 20;
    }
}
