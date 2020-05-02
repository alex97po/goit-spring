package com.pogorelov.goitspring;

import com.pogorelov.goitspring.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    App(Client client , EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
        app.logEvent(context,"Some event for user 1");
        app.logEvent(context, "Some event for user 2");
        context.close();
    }

    private void logEvent(ApplicationContext applicationContext, String msg) {
        Event message = (Event) applicationContext.getBean("event");
        eventLogger.logEvent(message);
    }
}
