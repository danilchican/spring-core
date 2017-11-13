package com.epam.danilchican.config;

import com.epam.danilchican.logger.EventLogger;
import com.epam.danilchican.logger.impl.CacheFileEventLogger;
import com.epam.danilchican.logger.impl.CombinedEventLogger;
import com.epam.danilchican.logger.impl.ConsoleEventLogger;
import com.epam.danilchican.logger.impl.FileEventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoggersConfig {

    @Bean
    public ConsoleEventLogger eventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean(initMethod = "init")
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("logs.txt");
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("logs.txt", 2);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger() {
        List<EventLogger> list = new ArrayList<EventLogger>() {{
           add(eventLogger());
           add(fileEventLogger());
           add(cacheFileEventLogger());
        }};

        return new CombinedEventLogger(list);
    }
}
