package com.xenecca.api.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	@Autowired
	CacheManager cacheManager;

	// every 13hrs
	@Scheduled(cron = "0 0 */13 * * *")
	public void evictCourseDataCachesAtInterval() {
		cacheManager.getCache("courses").clear();
		cacheManager.getCache("categories").clear();
		cacheManager.getCache("subcategories").clear();
	}

	// every 12 hours
	@Scheduled(cron = "0 0 */18 * * *")
	public void evictResourceCacheAtInterval() {
		cacheManager.getCache("resources").clear();
	}

	// every 24 hours
	@Scheduled(cron = "0 0 */24 * * *")
	public void evictResourceCategoriesCacheAtInterval() {
		cacheManager.getCache("resources").clear();
	}

}