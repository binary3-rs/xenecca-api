package com.xenecca.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/**")
                .allowedOrigins("https://xenecca.com", "https://admin.xenecca.com")
                .allowedHeaders("Authorization", "Content-Type", "Accept", "Authorization", "Origin")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS", "HEAD");
    }
}

