package com.lxy.pink.widget.FloorsView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.lxy.pink.R;

import java.util.List;

/**
 * Created by homelajiang on 2017/5/5 0005.
 */

public class FloorsView extends LinearLayout {

    private int mMaxNum = 10;

    public FloorsView(Context context) {
        this(context, null);
    }

    public FloorsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setBackgroundColor(Color.parseColor("#FFFEEE"));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (!isPressed()) {
            final int i = getChildCount();
            Drawable mBorder = ContextCompat.getDrawable(getContext(), R.drawable.floors_border);
            for (int j = 0; j < i; j++) {
                View child = getChildAt(j);
                mBorder.setBounds(child.getLeft(), child.getLeft(), child.getRight(), child.getBottom());
                mBorder.draw(canvas);
            }
        }

        super.dispatchDraw(canvas);
    }

    public void setViewList(List<View> commentViewList) {
        if (commentViewList == null || commentViewList.isEmpty()) {
            removeAllViewsInLayout();
            return;
        }
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        int size = commentViewList.size();
        int j = 0;
        for (int i = size - 1; i >= 0; i--) {
            LinearLayout.LayoutParams params = generateDefaultLayoutParams();
            int k = space * i;
            if (i > 6) {
                k = space * 6;
            }
            params.leftMargin = k;
            params.rightMargin = k;
            params.topMargin = j == 0 ? k : 0;
            View v = commentViewList.get(i);
            addViewInLayout(v, j++, params);
        }
    }
}
