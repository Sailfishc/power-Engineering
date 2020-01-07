package com.sailfish.engineering.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sailfish
 * @create 2019-12-27-11:30 AM
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }

}
