package com.lxy.pink.ui.home.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.core.PinkService;
import com.lxy.pink.core.PinkServiceContract;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.widget.ExListView;
import com.lxy.pink.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkCalendarView extends CardView implements PinkServiceContract.TodoCallback {
    @BindView(R.id.exListView)
    public ExListView mExListView;
    @BindView(R.id.empty_list_view)
    public TextView mEmptyListView;
    private TodoAdapter adapter;
    private View header;
    private TextView todoCount;

    private PinkServiceContract.Presenter presenter;
    private PinkService pinkService;

    public PinkCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.pink_home_calendar_view, this);
        ButterKnife.bind(this);
        header = View.inflate(getContext(), R.layout.item_home_todo_header, null);
        todoCount = (TextView) header.findViewById(R.id.todo_count);

        mExListView.addHeaderView(header);
        mExListView.setEmptyView(mEmptyListView);
        adapter = new TodoAdapter(getContext());
        mExListView.setAdapter(adapter);
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        if (todoList == null)
            return;
        todoCount.setText(String.format(getContext().getString(R.string.pink_todo_count), todoList.getTodoList().size()));
        adapter.setList(todoList.getTodoList());
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void bind(PinkService pinkService, TodoList todoList) {
        this.pinkService = pinkService;
        this.pinkService.bindTodoCallback(this);
        todoListLoaded(todoList);
    }

    public void unBind() {
        this.pinkService.unBindTodoCallback(this);
    }

    public class TodoAdapter extends BaseAdapter {

        private final Context context;
        List<Todo> todos = new ArrayList<>();

        public TodoAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return todos.size();
        }

        @Override
        public Todo getItem(int position) {
            return todos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Todo todo = todos.get(position);
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_todo_item, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            long start = Long.parseLong(todo.getDtstart());
            long end = Long.parseLong(todo.getDtend());

            if (end - start == 86400000) {
                viewHolder.time.setText(context.getString(R.string.pink_todo_all_day));
            } else {
                viewHolder.time.setText(TimeUtils.formatToTime(Long.parseLong(todo.getDtstart())));
            }
            viewHolder.content.setText(todo.getTitle());
            return convertView;
        }

        public void setList(List<Todo> todos) {
            this.todos = todos;
            notifyDataSetChanged();
        }

        class ViewHolder {
            public View itemView;
            public TextView time;
            public TextView content;

            ViewHolder(View itemView) {
                this.itemView = itemView;
                this.time = (TextView) itemView.findViewById(R.id.time);
                this.content = (TextView) itemView.findViewById(R.id.content);
            }
        }
    }

}
