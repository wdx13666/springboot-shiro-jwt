package com.wdx.springbootshirojwt.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import javax.annotation.Resource;

public class RedisCacheManager implements CacheManager {

    @Resource
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return redisCache;
    }
}
