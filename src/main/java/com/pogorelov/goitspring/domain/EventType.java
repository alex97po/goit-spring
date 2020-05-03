package com.pogorelov.goitspring.domain;

public enum EventType {

    INFO, ERROR;

    //default - CacheFileEventLogger
    //INFO - ConsoleEventLogger
    //ERROR - all loggers (Console + File)
}
