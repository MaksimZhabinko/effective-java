package com.epam.effectiveJava.listener;

import com.epam.effectiveJava.dto.ValueObject;

public interface RemovalListener {
    void logMessage(Integer key, ValueObject valueObject);
}
