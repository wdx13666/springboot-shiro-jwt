package com.wdx.springbootshirojwt.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Slf4j
@Component
public class RedisCache<K,V> implements Cache<K,V> {

    private final String CACHE_PREFIX = "redis-cache";

    @Override
    public V get(K k) throws CacheException {
        log.info("从redis中取数据");
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
