package com.lxy.pink.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by homelajiang on 2016/10/26 0026.
 */

public class ExListView extends ListView {

    public ExListView(Context context) {
        super(context);
    }

    public ExListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int spec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, spec);
    }
}
