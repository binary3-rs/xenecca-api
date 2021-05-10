package com.xenecca.api.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	@Autowired
	CacheManager cacheManager;

	public void evictAllCaches() {
		cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}

	// every 12:30 - 00:30 
	@Scheduled(cron = "0 30 */12 * * *")
	public void evictAllcachesAtIntervals() {
		evictAllCaches();
	}

}