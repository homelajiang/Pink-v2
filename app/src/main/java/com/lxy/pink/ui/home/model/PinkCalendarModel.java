package com.lxy.pink.ui.home.model;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.ui.home.view.PinkCalendarView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkCalendarModel extends EpoxyModel<PinkCalendarView> {

    @EpoxyAttribute
    TodoList todoList;

    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_calendar_model;
    }

    @Override
    public void bind(PinkCalendarView view) {
        view.setData(todoList.getTodoList());
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
