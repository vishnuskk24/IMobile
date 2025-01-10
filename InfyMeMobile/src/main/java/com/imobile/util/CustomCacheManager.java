package com.imobile.util;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CustomCacheManager {
	
	@Bean(name = "cacheManager1")
	@Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
            Caffeine.newBuilder()
                    .expireAfterWrite(10, TimeUnit.MINUTES) // Cache expiration time
                    .maximumSize(1000) // Max number of entries in cache
        );
        return cacheManager;
    }
	

//  ===================caching methods

	@CachePut(value = "userOtpCache", key = "#email")
	public String storeOTPInCache(String email, String otp) {
	    System.out.println("Storing OTP for email: " + email + " with OTP: " + otp);
		return otp; // This value is stored in the cache
	}
	@Cacheable(value = "userOtpCache", key = "#email")
	public String getOtpInCache(String email) {
		// This will not execute if the key is found in the cache
		return "OTP not available for email: " + email;
	}

	

	@CacheEvict(value = "userOtpCache", key = "#email")
	public void removeOtpInCache(String email) {
		System.out.println("Removed OTP for email: " + email);
	}

	@CacheEvict(value = "userOtpCache", allEntries = true)
	public void clearAll() {
		System.out.println("All cache entries cleared.");
	}


}
