package com.xenecca.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xenecca.api.utils.DbSeedUtils;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")

@ComponentScan(basePackages = "com.xenecca.api")
@Configuration
@SpringBootApplication
@Getter
public class ApiApplication {

	@Autowired
	private DbSeedUtils _dbSeedUtils;

	@Bean
	CommandLineRunner runner() {
		return args -> {
			getDbSeedUtils().addUser();
			getDbSeedUtils().populateResourceCategories();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
