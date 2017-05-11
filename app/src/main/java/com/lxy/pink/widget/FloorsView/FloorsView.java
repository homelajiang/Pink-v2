package com.lxy.pink.widget.FloorsView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoComment;

import java.util.List;

/**
 * Created by homelajiang on 2017/5/5 0005.
 */

public class FloorsView extends LinearLayout {

    private final int mLineSize;
    private final Paint mPaint;
    private int mMaxNum = 10;
    private Drawable drawable;
    private List<ACVideoComment> datas;

    public FloorsView(Context context) {
        this(context, null);
    }

    public FloorsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
//        setBackgroundColor(Color.parseColor("#FFFEEE"));
        drawable = ContextCompat.getDrawable(context, R.drawable.floors_bound);
        this.mLineSize = (int) TypedValue.applyDimension(1, 0.5f, context.getResources().getDisplayMetrics());
        this.mPaint = new Paint(1);
        this.mPaint.setColor(context.getResources().getColor(R.color.quote_border));
        this.mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() <= 0) {
            setMeasuredDimension(0, 0);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int childCount = getChildCount();
        if (this.drawable != null && childCount > 0) {
            for (int i = childCount - 1; i >= 0; i--) {
                View child = getChildAt(i);
                if (i > childCount - 6) {
                    this.drawable.setBounds(child.getLeft(), child.getLeft(), child.getRight(), child.getBottom());
                    this.drawable.draw(canvas);
                } else {
                    canvas.drawRect((float) child.getLeft(), (float) child.getBottom(), (float) child.getRight(), (float) (child.getBottom() + this.mLineSize), this.mPaint);
                }
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
