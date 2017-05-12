package com.lxy.pink.widget.FloorsView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACComment;
import com.lxy.pink.utils.FrescoUtils;
import com.lxy.pink.utils.FuzzyDateFormatter;

import java.util.Date;
import java.util.List;

/**
 * Created by homelajiang on 2017/5/5 0005.
 */

public class FloorsView extends LinearLayout {

    private final int mLineSize;
    private final Paint mPaint;
    private final int density;
    private int mMaxNum = 10;
    private Drawable drawable;
    private List<ACComment> datas;

    public FloorsView(Context context) {
        this(context, null);
    }

    public FloorsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
//        setBackgroundColor(Color.parseColor("#FFFEEE"));
        this.density = (int) getResources().getDimension(R.dimen.quote_indentation);
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

    public void setQuoteList(List<ACComment> commentViewList) {
        this.datas = commentViewList;
        removeAllViews();
        if (datas == null || datas.isEmpty()) {
            return;
        }
        for (ACComment comment : this.datas) {
            View view = getQuoteItemView(comment);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            addView(view);
        }
        reLayoutChildren();
    }

    private void reLayoutChildren() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int min = Math.min((childCount - i) - 1, 4) * this.density;
            layoutParams.leftMargin = min;
            layoutParams.rightMargin = min;
            if (i == childCount - 1) {
                layoutParams.topMargin = 0;
            } else {
                layoutParams.topMargin = Math.min(childCount - i, 4) * this.density;
            }
            childAt.setLayoutParams(layoutParams);
        }
    }

    private View getQuoteItemView(ACComment comment) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.ac_comment_quote, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        SimpleDraweeView headIcon = (SimpleDraweeView) view.findViewById(R.id.simpleDraweeView);
        TextView publishTime = (TextView) view.findViewById(R.id.publish_time);
        TextView floor = (TextView) view.findViewById(R.id.floor);
        TextView content = (TextView) view.findViewById(R.id.content);
        name.setText(comment.getUsername());
        int nameColor = comment.getNameRed() == 0 ? android.R.color.holo_red_dark : R.color.black;
        name.setTextColor(ContextCompat.getColor(getContext(), nameColor));
        FrescoUtils.setImage(comment.getAvatar(), headIcon);
        content.setText(comment.getContent());
        floor.setText(String.valueOf("#" + comment.getFloor()));
        publishTime.setText(FuzzyDateFormatter.getTimeAgo(getContext(), new Date(comment.getTime())));
        return view;
    }
}
