package com.wdx.springbootshirojwt.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

public interface CacheManager {
    /**
     * 根据缓存名字获取一个Cache
     * @param name
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    public <K, V> Cache<K, V> getCache(String name) throws CacheException;
}
