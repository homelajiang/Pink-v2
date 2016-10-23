package com.lxy.pink.data.model.todo;

/**
 * Created by yuan on 2016/10/20.
 */

public class Todo {
    private String calendar_id;
    private String title;
    private String dtstart;
    private String dtend;
    private String rrule;
    private String event_timezone;

    public String getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(String calendar_id) {
        this.calendar_id = calendar_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDtstart() {
        return dtstart;
    }

    public void setDtstart(String dtstart) {
        this.dtstart = dtstart;
    }

    public String getDtend() {
        return dtend;
    }

    public void setDtend(String dtend) {
        this.dtend = dtend;
    }

    public String getRrule() {
        return rrule;
    }

    public void setRrule(String rrule) {
        this.rrule = rrule;
    }

    public String getEvent_timezone() {
        return event_timezone;
    }

    public void setEvent_timezone(String event_timezone) {
        this.event_timezone = event_timezone;
    }
}
