package com.pogorelov.goitspring.loggers;

import com.pogorelov.goitspring.domain.Event;

public interface EventLogger {

    void logEvent(Event event);
}
