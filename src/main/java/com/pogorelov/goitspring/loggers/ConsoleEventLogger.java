package com.pogorelov.goitspring.loggers;


import com.pogorelov.goitspring.domain.Event;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event){
        System.out.println(event.toString());
    }
}
