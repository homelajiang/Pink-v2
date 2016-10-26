package com.lxy.pink.data.retrofit.api;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yuan on 2016/10/23.
 */

public class TodoService {


    //query
    public Observable<TodoList> queryTodo(final ContentResolver cr) {
        return Observable.create(new Observable.OnSubscribe<TodoList>() {
            @Override
            public void call(Subscriber<? super TodoList> subscriber) {
                subscriber.onStart();

                Uri calenderEventUri = Uri.parse("content://com.android.calendar/events");

                String options = CalendarContract.Events.DTSTART + ">=? and "
                        + CalendarContract.Events.DTSTART
                        + "<=?";

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                Cursor cursor = cr.query(calenderEventUri,
                        new String[]{
                                CalendarContract.Events.CALENDAR_ID,
                                CalendarContract.Events.TITLE,
                                CalendarContract.Events.DTSTART,
                                CalendarContract.Events.DTEND,
                                CalendarContract.Events.RRULE,
                                CalendarContract.Events.EVENT_TIMEZONE
                        },
                        options,
                        new String[]{
                                String.valueOf(calendar.getTimeInMillis()),
                                String.valueOf(calendar.getTimeInMillis() + 86399999)
                        }, null);
                List<Todo> todoList = new ArrayList<>();
                while (cursor != null && cursor.moveToNext()) {
                    Todo tempTodo = new Todo();
                    tempTodo.setCalendar_id(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.CALENDAR_ID)));
                    tempTodo.setTitle(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE)));
                    tempTodo.setDtstart(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTSTART)));
                    tempTodo.setDtend(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTEND)));
                    tempTodo.setRrule(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.RRULE)));
                    tempTodo.setEvent_timezone(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.EVENT_TIMEZONE)));
                    todoList.add(tempTodo);
                }
                if (cursor != null) {
                    cursor.close();
                }
                subscriber.onNext(new TodoList(todoList));
                subscriber.onCompleted();
            }
        });
    }

    //insert
    public Observable<Void> insertTodo(final ContentResolver cr, Todo todo) {
        return null;
    }

    //update
    public Observable<Void> updateTodo(final ContentResolver cr, Todo todo) {
        return null;
    }

    //delete
    public Observable<Void> removeTodo(final ContentResolver cr, String calendarId) {
        return null;
    }
}
