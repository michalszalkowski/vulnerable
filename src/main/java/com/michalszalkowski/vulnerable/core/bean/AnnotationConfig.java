package com.michalszalkowski.vulnerable.core.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {

	@Bean(name = "dog")
	Dog getDog() {
		return new Dog("Hardcoded dog name");
	}

	@Bean(name = "cat")
	Cat getCat() {
		return new Cat("Hardcoded cat name");
	}
}
