package com.pogorelov.goitspring.loggers;


import com.pogorelov.goitspring.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event){
        System.out.println(event.toString());
    }
}
