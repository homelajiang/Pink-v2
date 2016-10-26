package com.lxy.pink.data.model.todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by homelajiang on 2016/10/26 0026.
 */

public class TodoList {
    private List<Todo> todoList = new ArrayList<>();

    public TodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public Todo getTodo(int index) {
        return this.todoList.get(index);
    }

    public int size() {
        return this.todoList.size();
    }
}
