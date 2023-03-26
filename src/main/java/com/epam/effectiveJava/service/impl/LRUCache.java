package com.epam.effectiveJava.service.impl;

import com.epam.effectiveJava.dto.ValueObject;
import com.epam.effectiveJava.service.CacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import lombok.extern.java.Log;

import java.util.concurrent.TimeUnit;

@Log
public class LRUCache implements CacheService {

    private final Cache<Integer, ValueObject> cache;
    private final int EXPIRE_ACCESS = 5;
    private final int CONCURRENCY_LEVEL = 8;

    public LRUCache(Integer capacity) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(capacity)
                .expireAfterAccess(EXPIRE_ACCESS, TimeUnit.SECONDS)
                .removalListener(removalNotification -> LOG.info(String.format("Was removed key - %s, with value - %s",
                        removalNotification.getKey(),
                        removalNotification.getValue())))
                .concurrencyLevel(CONCURRENCY_LEVEL)
                .recordStats()
                .build();
    }

    @Override
    public ValueObject get(Integer key) {
        return cache.getIfPresent(key);
    }

    @Override
    public void put(Integer key, ValueObject valueObject) {
        cache.put(key, valueObject);
    }

    public long size() {
        return cache.size();
    }

    public void logStats() {
        CacheStats stats = cache.stats();
        LOG.info(String.format("Average time spent for putting new values into the cache - %s, number of cache evictions - %s",
                stats.averageLoadPenalty(),
                stats.evictionCount()));
    }
}
