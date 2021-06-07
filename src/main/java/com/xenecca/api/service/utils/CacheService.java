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

	// every 13hrs
	@Scheduled(cron = "0 0 */13 * * *")
	public void evictCourseDataCachesAtInterval() {

		getCacheManager().getCache("courses-by-page").clear();
		// getCacheManager().getCache("categories").clear(); -- categories are
		// predefined
		getCacheManager().getCache("subcategories").clear();
	}

	// every 3rd day
	@Scheduled(cron = "0 0 0 */3 * *")
	public void evictResourceCacheAtInterval() {
		getCacheManager().getCache("resources").clear();
		getCacheManager().getCache("resources-by-category").clear();
		getCacheManager().getCache("resource-categories").clear();
	}

	/*
	 * // on 1st day of the month
	 * 
	 * @Scheduled(cron = "0 0 6 1 * *") public void
	 * evictResourceTypesCacheAtInterval() {
	 * getCacheManager().getCache("resource-domains").clear();
	 * getCacheManager().getCache("resource-types").clear();
	 * 
	 * }
	 */

}