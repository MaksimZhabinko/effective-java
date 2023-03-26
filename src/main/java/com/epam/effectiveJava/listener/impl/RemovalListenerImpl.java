package com.epam.effectiveJava.listener.impl;

import com.epam.effectiveJava.dto.ValueObject;
import com.epam.effectiveJava.listener.RemovalListener;
import lombok.extern.java.Log;

@Log
public class RemovalListenerImpl implements RemovalListener {
    @Override
    public void logMessage(Integer key, ValueObject valueObject) {
        LOG.info(String.format("Was removed key - %s, with value - %s",
                key,
                valueObject));
    }
}
