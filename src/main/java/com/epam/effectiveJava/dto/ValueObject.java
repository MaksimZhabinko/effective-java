package com.epam.effectiveJava.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ValueObject {
    private long lastAccessed = System.currentTimeMillis();
    private final String value;
}
