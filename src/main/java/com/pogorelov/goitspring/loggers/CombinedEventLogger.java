package com.pogorelov.goitspring.loggers;

import com.pogorelov.goitspring.domain.Event;
import lombok.Data;

import java.util.List;

@Data
public class CombinedEventLogger extends FileEventLogger implements EventLogger {

    private List<EventLogger> loggers;

    CombinedEventLogger(String fileName, List<EventLogger> loggers) {
        super(fileName);
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
        super.logEvent(event);
    }
}
