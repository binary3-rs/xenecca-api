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

	// every 4th hour at :30 mins
	@Scheduled(cron = "0 30 */4 * * *")
	public void evictCourseCachesAtInterval() {
		// TODO: add null pointer exception protection
		getCacheManager().getCache("courses").clear();
		getCacheManager().getCache("courses:similar").clear();
		getCacheManager().getCache("courses:top").clear();
		getCacheManager().getCache("courses:page").clear();
		getCacheManager().getCache("subcategories").clear();
	}

	// every 3rd day
	@Scheduled(cron = "0 0 0 */3 * *")
	public void evictResourceCacheAtInterval() {
		getCacheManager().getCache("resources").clear();
		getCacheManager().getCache("resources:category").clear();
		getCacheManager().getCache("resources:categories").clear();
	}

}