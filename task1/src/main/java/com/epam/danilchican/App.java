package com.epam.danilchican;

import com.epam.danilchican.event.Event;
import com.epam.danilchican.event.EventType;
import com.epam.danilchican.logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;
    private EventLogger defaultLogger;

    public App() {
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        System.out.println(app.client);
        Event event = (Event) ctx.getBean("event");
        app.logEvent(event, EventType.ERROR);
        ctx.close();
    }

    private void logEvent(Event event, EventType type) {
        EventLogger logger = loggers.get(type);

        if(logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
