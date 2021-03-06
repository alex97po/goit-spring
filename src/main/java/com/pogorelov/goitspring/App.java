package com.pogorelov.goitspring;

import com.pogorelov.goitspring.config.AppConfig;
import com.pogorelov.goitspring.config.CollectionConfig;
import com.pogorelov.goitspring.domain.Client;
import com.pogorelov.goitspring.domain.Event;
import com.pogorelov.goitspring.domain.EventType;
import com.pogorelov.goitspring.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("app")
public class App {

    @Autowired
    private Client client;

    @Value("#{T(com.pogorelov.goitspring.domain.Event).isDay() ? fileEventLogger : consoleEventLogger}")
    private EventLogger defaultLogger;

    @Autowired
    private Map<EventType, EventLogger> loggerMap;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(CollectionConfig.class, AppConfig.class);
        App app = (App) context.getBean("app");
        List<Event> events = Stream.generate(() -> (Event) context.getBean("event")).limit(3).collect(Collectors.toList());
        app.appLogEvent(EventType.ERROR, events.get(0), "Some event for user 1");
        app.appLogEvent(EventType.INFO, events.get(1), "Some event for user 2");
        app.appLogEvent(null, events.get(2), "Some event for user 3");
        context.close();
    }

    private void appLogEvent(EventType type, Event event, String msg) {
        String message = msg.replaceAll(client.getId().toString(), client.getFullName());
        event.setMessage(message);

        EventLogger logger = Objects.isNull(type) ? defaultLogger : loggerMap.get(type);

        logger.logEvent(event);
    }
}
