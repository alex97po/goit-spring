package com.pogorelov.goitspring;

import com.pogorelov.goitspring.domain.Client;
import com.pogorelov.goitspring.domain.Event;
import com.pogorelov.goitspring.domain.EventType;
import com.pogorelov.goitspring.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
        List<Event> events = Stream.generate(() -> (Event) context.getBean("event")).limit(3).collect(Collectors.toList());
        app.logEvent(EventType.ERROR, events.get(0), "Some event for user 1");
        app.logEvent(EventType.INFO, events.get(1), "Some event for user 2");
        app.logEvent(null, events.get(2), "Some event for user 3");
        context.close();
    }

    private void logEvent(EventType type, Event event, String msg) {
        String message = msg.replaceAll(client.getId().toString(), client.getFullName());
        event.setMessage(message);

        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
