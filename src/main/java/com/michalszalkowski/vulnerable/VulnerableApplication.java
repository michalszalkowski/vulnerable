package com.michalszalkowski.vulnerable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VulnerableApplication {

	public static void main(String[] args) {
		SpringApplication.run(VulnerableApplication.class, args);
	}

}
