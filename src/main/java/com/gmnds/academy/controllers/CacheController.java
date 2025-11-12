package com.gmnds.academy.controllers;

import com.gmnds.academy.infra.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @PostMapping
    public void clearCache(@RequestParam String cacheName) {
        cacheService.evictAllCachesValues(cacheName);
    }

}
