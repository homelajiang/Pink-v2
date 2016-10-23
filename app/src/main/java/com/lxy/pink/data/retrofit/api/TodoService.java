package com.lxy.pink.data.retrofit.api;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import com.lxy.pink.data.model.todo.Todo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yuan on 2016/10/23.
 */

public class TodoService {


    //query
    public Observable<List<Todo>> queryTodo(final ContentResolver cr, final long startTimeMillis) {
        return Observable.create(new Observable.OnSubscribe<List<Todo>>() {
            @Override
            public void call(Subscriber<? super List<Todo>> subscriber) {
                subscriber.onStart();

                Uri calenderEventUri = Uri.parse("content://com.android.calendar/events");

                String options = CalendarContract.Events.DTSTART + ">=? and "
                        + CalendarContract.Events.DTSTART
                        + "<=?";

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
                                String.valueOf(startTimeMillis),
                                String.valueOf(startTimeMillis + 86400000)
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
                subscriber.onNext(todoList);
                subscriber.onCompleted();
            }
        });
    }

    //insert
    public Observable<Void> insertTodo(final ContentResolver cr,Todo todo) {
        return null;
    }

    //update
    public Observable<Void> updateTodo(final ContentResolver cr,Todo todo) {
        return null;
    }

    //delete
    public Observable<Void> removeTodo(final ContentResolver cr,String calendarId) {
        return null;
    }
}
