package com.pogorelov.goitspring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Client {

    @Value("${id}")
    private Long id;

    @Value("${name}")
    private String fullName;

    @Value("${greeting}")
    private String greeting;
}
