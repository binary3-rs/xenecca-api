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

	// every day at 3:30
	@Scheduled(cron = "0 30 3 */1 * *")
	public void evictCacheDaily() {
		getCacheManager().getCache("courses").clear();
		getCacheManager().getCache("similar-courses").clear();
		getCacheManager().getCache("top-courses").clear();
	}

	// every 3rd hour at :30 mins
	@Scheduled(cron = "0 30 */3 * * *")
	public void evictCourseDataCachesAtInterval() {
		getCacheManager().getCache("courses-by-page").clear();
		getCacheManager().getCache("subcategories").clear();
	}

	// every 3rd day
	@Scheduled(cron = "0 0 0 */3 * *")
	public void evictResourceCacheAtInterval() {
		getCacheManager().getCache("resources").clear();
		getCacheManager().getCache("resources-by-category").clear();
		getCacheManager().getCache("resource-categories").clear();
	}

}