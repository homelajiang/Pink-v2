package com.lxy.pink.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by homelajiang on 2016/10/26 0026.
 */

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
