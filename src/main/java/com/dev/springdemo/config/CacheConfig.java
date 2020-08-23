package com.dev.springdemo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        Caffeine<Object, Object> caffeineCacheBuilder =
            Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterAccess(
                        5, TimeUnit.MINUTES);

        CaffeineCacheManager cacheManager =
                new CaffeineCacheManager(
                "customers", "roles", "users");
        cacheManager.setCaffeine(caffeineCacheBuilder);
        return cacheManager;
    }
}
