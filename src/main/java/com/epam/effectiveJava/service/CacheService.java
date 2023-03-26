package com.epam.effectiveJava.service;

import com.epam.effectiveJava.dto.ValueObject;

public interface CacheService {
    ValueObject get(Integer key);

    void put(Integer key, ValueObject valueObject);
}
