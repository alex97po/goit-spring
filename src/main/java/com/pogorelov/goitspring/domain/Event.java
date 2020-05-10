package com.pogorelov.goitspring.domain;

import lombok.Data;

import javax.sound.midi.MidiFileFormat;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class Event {

    private Long id;

    private String message;

    private Date date;

    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public static boolean isDay() {
        return LocalDateTime.now().isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)))
                && LocalDateTime.now().isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
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
