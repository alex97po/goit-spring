package com.pogorelov.goitspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    App(Client client , EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId().toString(), client.getFullName());
        eventLogger.logEvent(message);
    }
}
