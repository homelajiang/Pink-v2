package com.lxy.pink.ui.music.model;

import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.ui.music.view.PinkCalendarView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkCalendarModel extends EpoxyModel<PinkCalendarView>{
    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_calendar_model;
    }

    @Override
    public void bind(PinkCalendarView view) {
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }
}
