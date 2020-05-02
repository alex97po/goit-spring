package com.pogorelov.goitspring;

public class App {

    private Client client;
    private ConsoleEventLogger eventLogger;

    public static void main(String[] args) {
        App app = new App();
        app.client = new Client(1L, "John Smith");
        app.eventLogger = new ConsoleEventLogger();
        app.logEvent("Some event for user 1");
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId().toString(), client.getFullName());
        eventLogger.logEvent(message);
    }
}
