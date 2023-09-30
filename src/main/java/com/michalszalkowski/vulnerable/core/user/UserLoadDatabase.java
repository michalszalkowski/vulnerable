package com.michalszalkowski.vulnerable.core.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserLoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(UserLoadDatabase.class);

	@Bean
	CommandLineRunner initUserDatabase(UserRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new UserEntity("michal", "hacker")));
			log.info("Preloading " + repository.save(new UserEntity("admin", "hacker")));
		};
	}
}