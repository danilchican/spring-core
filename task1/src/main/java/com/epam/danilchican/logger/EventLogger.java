package com.epam.danilchican.logger;

import com.epam.danilchican.event.Event;

public interface EventLogger {
    void logEvent(Event event);
}
