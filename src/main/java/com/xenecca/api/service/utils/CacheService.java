package com.xenecca.api.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = { "_" })
@Service
@Getter
public class CacheService {

	@Autowired
	private CacheManager _cacheManager;

	// every 13hrs
	@Scheduled(cron = "0 0 */13 * * *")
	public void evictCourseDataCachesAtInterval() {
		getCacheManager().getCache("courses").clear();
		getCacheManager().getCache("categories").clear();
		getCacheManager().getCache("subcategories").clear();
	}

	// every 12 hours
	@Scheduled(cron = "0 0 */18 * * *")
	public void evictResourceCacheAtInterval() {
		getCacheManager().getCache("resources").clear();
	}

	// every 24 hours
	@Scheduled(cron = "0 0 */24 * * *")
	public void evictResourceCategoriesCacheAtInterval() {
		getCacheManager().getCache("resources").clear();
	}

}