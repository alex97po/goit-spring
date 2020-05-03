package com.pogorelov.goitspring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Client {

    private final Long id;

    private final String fullName;

    private String greeting;
}
