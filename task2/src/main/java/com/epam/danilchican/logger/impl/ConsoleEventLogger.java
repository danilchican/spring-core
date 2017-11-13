package com.epam.danilchican.logger.impl;

import com.epam.danilchican.event.Event;
import com.epam.danilchican.logger.EventLogger;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
