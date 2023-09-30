package com.michalszalkowski.vulnerable.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConfigLoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(ConfigLoadDatabase.class);

	@Bean
	CommandLineRunner initConfigDatabase(ConfigRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new ConfigEntity("user", "administrator")));
			log.info("Preloading " + repository.save(new ConfigEntity("pass", "pass@1234$")));
		};
	}

}
