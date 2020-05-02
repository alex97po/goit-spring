package com.pogorelov.goitspring.loggers;

import com.pogorelov.goitspring.Event;

public interface EventLogger {

    void logEvent(Event event);
}
