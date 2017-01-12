package com.lxy.pink.ui.home.model;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.core.PinkServiceContract;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.ui.home.impl.PinkCalendarCallback;
import com.lxy.pink.ui.home.view.PinkCalendarView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkCalendarModel extends EpoxyModel<PinkCalendarView> implements PinkCalendarCallback {

    TodoList todoList;
    @EpoxyAttribute
    PinkServiceContract.Presenter presenter;
    private PinkCalendarView calendarView;

    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_calendar_model;
    }

    @Override
    public void bind(PinkCalendarView view) {
        if (this.todoList == null)
            return;
        this.calendarView = view;
        todoListLoaded(this.todoList);
    }

    @Override
    public void unbind(PinkCalendarView view) {
        super.unbind(view);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        this.todoList = todoList;
        if (todoList == null || calendarView == null) {
            return;
        }
        calendarView.setData(this.todoList.getTodoList());
    }
}
