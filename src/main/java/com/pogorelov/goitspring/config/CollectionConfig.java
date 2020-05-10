package com.pogorelov.goitspring.config;

import com.pogorelov.goitspring.domain.Event;
import com.pogorelov.goitspring.domain.EventType;
import com.pogorelov.goitspring.loggers.CombinedEventLogger;
import com.pogorelov.goitspring.loggers.ConsoleEventLogger;
import com.pogorelov.goitspring.loggers.EventLogger;
import com.pogorelov.goitspring.loggers.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("com.pogorelov.goitspring")
@PropertySource("classpath:client.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CollectionConfig {

    @Autowired
    private ConsoleEventLogger consoleEventLogger;

    @Autowired
    private FileEventLogger fileEventLogger;

    @Autowired
    private CombinedEventLogger combinedEventLogger;

    @Bean
    public List<EventLogger> loggers() {
        return List.of(consoleEventLogger, fileEventLogger);
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        return Map.of(EventType.INFO, consoleEventLogger, EventType.ERROR, combinedEventLogger);
    }

}
