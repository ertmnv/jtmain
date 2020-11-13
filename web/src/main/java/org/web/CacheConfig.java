package org.web;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    private static final int EXPIRE_TIME = 30;
    private static final int MAXIMUM_NUMBER_OF_ENTRIES = 100;

    @Bean
    @Override
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name,
                        CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_TIME, TimeUnit.SECONDS)
                                .maximumSize(MAXIMUM_NUMBER_OF_ENTRIES).build().asMap(),
                        false);
            }
        };

        cacheManager.setCacheNames(Arrays.asList("courses"));
        return cacheManager;
    }

}
