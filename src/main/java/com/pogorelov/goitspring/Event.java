package com.pogorelov.goitspring;

import lombok.Data;

import java.text.DateFormat;
import java.util.Date;

@Data
public class Event {

    private Long id;

    private String message;

    private Date date;

    private DateFormat dateFormat;

    Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
