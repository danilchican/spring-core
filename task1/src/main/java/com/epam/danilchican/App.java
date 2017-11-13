package com.epam.danilchican;

import com.epam.danilchican.event.Event;
import com.epam.danilchican.event.EventType;
import com.epam.danilchican.logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App() {
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        app.logEvent(event, EventType.ERROR);
        ctx.close();
    }

    private void logEvent(Event event, EventType type) {
        eventLogger.logEvent(event);
    }
}
