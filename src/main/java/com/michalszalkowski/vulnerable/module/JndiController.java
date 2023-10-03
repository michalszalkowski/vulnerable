package com.michalszalkowski.vulnerable.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JndiController {

	@Autowired
	private ApplicationContext context;

	private static final Logger log = LoggerFactory.getLogger(JndiController.class);

	@GetMapping("/vun/jndi/example1/{test-id}")
	private Object byQueryParam(@RequestParam String payload, @PathVariable("test-id") String testId) throws Exception {
		log.info("JNDI (example1 - " + testId + "):" + payload);
		return context.getBean(payload);
	}

}
