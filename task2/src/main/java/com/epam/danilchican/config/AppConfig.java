package com.epam.danilchican.config;

import com.epam.danilchican.App;
import com.epam.danilchican.Client;
import com.epam.danilchican.event.Event;
import com.epam.danilchican.event.EventType;
import com.epam.danilchican.logger.EventLogger;
import com.epam.danilchican.logger.impl.ConsoleEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Import({LoadersConfig.class, LoggersConfig.class})
public class AppConfig {

    @Autowired
    private LoggersConfig loggersConfig;

    @Autowired
    private ConsoleEventLogger eventLogger;

    @Bean
    public Client client() {
        Client client = new Client("1", "John Doe");
        client.setGreeting("My Greeting");

        return client;
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), DateFormat.getDateTimeInstance());
    }

    @Bean
    public App app() {
        Map<EventType, EventLogger> map = new HashMap<EventType, EventLogger>() {{
            put(EventType.INFO, loggersConfig.eventLogger());
            put(EventType.ERROR, loggersConfig.combinedEventLogger());
        }};

        return new App(client(), eventLogger, map);
    }
}
