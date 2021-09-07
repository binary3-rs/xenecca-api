package com.xenecca.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.xenecca.api.service.utils.CacheService;
import com.xenecca.api.service.utils.DbSeedUtils;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")

@ComponentScan(basePackages = "com.xenecca.api")
@Configuration
@EnableCaching
@SpringBootApplication
@Getter
@EnableScheduling
public class ApiApplication extends SpringBootServletInitializer {

    @Autowired
    private DbSeedUtils _dbSeedUtils;

    @Autowired
    private CacheService _cacheService;

    @Autowired
    private CacheManager _cacheManager;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            getDbSeedUtils().addUser();
            getDbSeedUtils().populateResourceCategories(1);
            getDbSeedUtils().populateCategories();
        };
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("categories", "subcategories", "resources:domains",
                "resources:type", "courses", "courses:similar", "courses:top", "courses:page", "resources",
                "resources:category", "resources:categories");
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }

}
