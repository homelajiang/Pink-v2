package com.lxy.pink.ui.home;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.ui.base.adapter.IAdapterView;
import com.lxy.pink.ui.widget.ExListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/31.
 */

public class TodoItemView extends CardView implements IAdapterView<TodoList> {
    private final Context context;
    @BindView(R.id.exListView)
    ExListView exListView;
    @BindView(R.id.empty_list_view)
    TextView emptyListView;
    private TextView count;
    private View headView;
    TodoAdapter todoAdapter;

    public TodoItemView(Context context) {
        super(context);
        this.context = context;
        View.inflate(context, R.layout.item_home_todo, this);
        ButterKnife.bind(this);
        headView = LayoutInflater.from(context).inflate(R.layout.item_home_todo_header, null);
        count = (TextView) headView.findViewById(R.id.todo_count);
        todoAdapter = new TodoAdapter(context);
//        exListView.setEmptyView(emptyListView);
        exListView.addHeaderView(headView);
        exListView.setAdapter(todoAdapter);
    }

    @Override
    public void bind(TodoList item, int position) {
        count.setText(String.format(context.getString(R.string.pink_todo_count), item.size()));
        List<Todo> temp = new ArrayList<>();
        long now = System.currentTimeMillis();

        for (Todo todo : item.getTodoList()) {
            long start = Long.parseLong(todo.getDtstart());
            long end = Long.parseLong(todo.getDtend());

            if (end - start == 86400000 || start >= now) {
                temp.add(todo);
            }
        }

        if (temp.size() == 0) {
            emptyListView.setVisibility(VISIBLE);
        }else {
            emptyListView.setVisibility(GONE);
        }
        todoAdapter.setList(temp);
    }
}
