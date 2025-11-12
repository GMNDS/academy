package com.gmnds.academy.infra.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    public void evictAllCachesValues(String cacheName) {
        Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();

    }

}
