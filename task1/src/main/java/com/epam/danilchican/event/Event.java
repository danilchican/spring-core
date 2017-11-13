package com.epam.danilchican.event;

import java.sql.Time;
import java.text.DateFormat;
import java.util.*;

public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event() {
        this.id = new Random().nextInt();
    }

    public Event(Date date, DateFormat df) {
        this();
        this.date = date;
        this.df = df;
    }

    public static boolean isDay() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        final int hours = calendar.get(Calendar.HOUR_OF_DAY);

        return hours > 8 && hours < 17;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
