package com.pogorelov.goitspring.config;

import com.pogorelov.goitspring.domain.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.Date;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), DateFormat.getDateTimeInstance());
    }
}
