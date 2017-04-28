package com.lxy.pink.ui.video;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.lxy.pink.BuildConfig;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Modifies item spacing in a recycler view so that items are equally spaced no matter where they
 * are on the grid.
 */
public class VerticalGridCardSpacingDecoration extends ItemDecoration {
    private static final int OUTER_PADDING_DP = 14;
    private static final int INNER_PADDING_DP = 7;
    private int outerPadding = -1;
    private int innerPadding = -1;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        if (outerPadding == -1 || innerPadding == -1) {
            DisplayMetrics m = view.getResources().getDisplayMetrics();
            outerPadding = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, OUTER_PADDING_DP, m);
            innerPadding = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, INNER_PADDING_DP, m);
        }

        int position = parent.getChildAdapterPosition(view);
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();

        // Zero everything out for the common case
        outRect.setEmpty();

        int spanSize = spanSizeLookup.getSpanSize(position);//item所占列数
        int spanCount = layoutManager.getSpanCount();//总列数
        int spanIndex = spanSizeLookup.getSpanIndex(position, spanCount);//item所在列数的开始index
        if (BuildConfig.DEBUG)
            Log.d("spanIndex", position + "-" + spanSize + '-' + spanCount + '-' + spanIndex);

        outRect.bottom = outerPadding;
        outRect.top = 0;
        if(position==0){//first item is placeHolder
            outRect.bottom = 0;
            return;
        }
        if(position==1){//'first' item
            outRect.left = 0;
            outRect.right = 0;
            return;
        }

        if(spanSize == spanCount){//one item in row
            outRect.left = outerPadding;
            outRect.right = outerPadding;
            return;
        }

        if(spanIndex==0){//not one item in row ,the first item in row
            outRect.left = outerPadding;
            outRect.right = innerPadding;
            return;
        }

        if(spanSize+spanIndex == spanCount){//not one item in row ,the last item in row
            outRect.left = innerPadding;
            outRect.right = outerPadding;
        }else {                             //not one item in row ,other item in row
            outRect.left = innerPadding;
            outRect.right = innerPadding;
        }

//
//    if (spanSize == spanCount) {
//      // Only item in row
//      outRect.left = outerPadding;
//      outRect.right = outerPadding;
//    }  else if (spanIndex == spanCount - 1) {
//      // Last item in row
//      outRect.left = innerPadding;
//      outRect.right = outerPadding;
//    } else {
//      // Inner item (not relevant for less than three columns)
//      outRect.left = innerPadding;
//      outRect.right = innerPadding;
//    }
//        if (position == 0) {
//            outRect.bottom = innerPadding;
//        } else {
//            outRect.left = innerPadding;
//            outRect.right = innerPadding;
//            outRect.bottom = innerPadding;
//        }
    }
}
