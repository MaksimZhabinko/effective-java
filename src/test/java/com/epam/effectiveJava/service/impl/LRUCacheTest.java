package com.epam.effectiveJava.service.impl;

import com.epam.effectiveJava.dto.ValueObject;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@Log
class LRUCacheTest {

    @Test
    void shouldAddAndRemoveObjects() {
        LRUCache cache = new LRUCache(6);
        cache.put(1, new ValueObject("eBay"));
        cache.put(2, new ValueObject("Paypal"));
        cache.put(3, new ValueObject("Google"));
        cache.put(4, new ValueObject("Microsoft"));
        cache.put(5, new ValueObject("Crunchify"));
        cache.put(6, new ValueObject("Facebook"));

        LOG.info(String.format("6 Cache Object Added.. cache.size(): %s", cache.size()));
        cache.put(7, new ValueObject("Twitter"));
        cache.put(8, new ValueObject("SAP"));
        LOG.info(String.format("Two objects Added but reached maxItems.. cache.size(): %s", cache.size()));

        assertThat(cache.size(), is(6L));
        assertThat(cache.get(1), nullValue());
        assertThat(cache.get(2), nullValue());

        cache.logStats();
    }

    @Test
    void shouldExpiredCacheObjects() {
        LRUCache cache = new LRUCache(10);
        cache.put(1, new ValueObject("eBay"));
        cache.put(2, new ValueObject("Paypal"));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertThat(cache.get(1), nullValue());
        assertThat(cache.get(2), nullValue());
        assertThat(cache.size(), is(0L));

        cache.logStats();
    }
}