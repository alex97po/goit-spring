package com.pogorelov.goitspring;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {

    private Long id;

    private String fullName;
}
