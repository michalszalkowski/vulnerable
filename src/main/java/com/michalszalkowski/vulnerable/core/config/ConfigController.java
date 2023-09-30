package com.michalszalkowski.vulnerable.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConfigController {

	@Autowired
	private ConfigRepository configRepository;

	@GetMapping("/api/config")
	private List<ConfigEntity> list() {
		return configRepository.findAll();
	}

}
