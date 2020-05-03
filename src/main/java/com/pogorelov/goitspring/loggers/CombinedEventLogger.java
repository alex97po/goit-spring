package com.pogorelov.goitspring.loggers;

import com.pogorelov.goitspring.domain.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@EqualsAndHashCode(callSuper = true)
public class CombinedEventLogger extends FileEventLogger implements EventLogger {

    @Autowired
    private List<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
        super.logEvent(event);
    }
}
