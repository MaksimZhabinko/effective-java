package com.epam.effectiveJava.service.impl;

import com.epam.effectiveJava.dto.ValueObject;
import com.epam.effectiveJava.service.CacheService;
import com.epam.effectiveJava.listener.RemovalListener;
import com.epam.effectiveJava.listener.impl.RemovalListenerImpl;
import com.epam.effectiveJava.util.Util;
import lombok.extern.java.Log;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.LRUMap;

import java.util.ArrayList;

@Log
public class LFUCache implements CacheService {

    private final long timeToLive;
    private final LRUMap<Integer, ValueObject> cacheMap;
    private final ArrayList<Integer> evictions = new ArrayList<>();
    private final ArrayList<Double> spentTimeForPut = new ArrayList<>();
    private static final RemovalListener REMOVAL_LISTENER = new RemovalListenerImpl();
    private static final long SECOND = 1000;

    public LFUCache(long timeToLive, final long timerInterval, int maxItems) {
        this.timeToLive = timeToLive * SECOND;
        cacheMap = new LRUMap<>(maxItems);
        if (this.timeToLive > 0 && timerInterval > 0) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(timerInterval * SECOND);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    crunchifyCleanup();
                }
            });
            t.setDaemon(true);
            t.start();
        }
    }

    @Override
    public ValueObject get(Integer key) {
        synchronized (cacheMap) {
            ValueObject valueObject = cacheMap.get(key);
            if (valueObject == null)
                return null;
            else {
                valueObject.setLastAccessed(System.currentTimeMillis());
                return valueObject;
            }
        }
    }

    @Override
    public void put(Integer key, ValueObject valueObject) {
        synchronized (cacheMap) {
            long startTime = System.nanoTime();
            if (cacheMap.isFull()) {
                Integer firstKey = cacheMap.firstKey();
                remove(firstKey);
            }
            cacheMap.put(key, valueObject);
            double spentTime = System.nanoTime() - startTime;
            spentTimeForPut.add(spentTime);
        }
    }

    public void remove(Integer key) {
        synchronized (cacheMap) {
            REMOVAL_LISTENER.logMessage(key, cacheMap.get(key));
            cacheMap.remove(key);
            evictions.add(key);
        }
    }

    public void logStats() {
        Double averageTime = Util.averageTime(spentTimeForPut);
        LOG.info(String.format("Average time spent for putting new values into the cache - %s millisecond, number of cache evictions - %s",
                averageTime,
                evictions.size()));
    }

    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    public void crunchifyCleanup() {
        long now = System.currentTimeMillis();
        ArrayList<Integer> deleteKey;
        synchronized (cacheMap) {
            MapIterator<Integer, ValueObject> iterator = cacheMap.mapIterator();
            deleteKey = new ArrayList<>((cacheMap.size() / 2) + 1);
            Integer key;
            ValueObject valueObject;
            while (iterator.hasNext()) {
                key = iterator.next();
                valueObject = iterator.getValue();
                if (valueObject != null && (now > (timeToLive + valueObject.getLastAccessed()))) {
                    deleteKey.add(key);
                }
            }
        }
        for (Integer key : deleteKey) {
            remove(key);
        }
    }
}
