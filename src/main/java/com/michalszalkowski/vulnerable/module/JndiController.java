package com.michalszalkowski.vulnerable.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JndiController {

	private static final Logger log = LoggerFactory.getLogger(JndiController.class);

	@GetMapping("/vun/jndi/example1/")
	private Object byQueryParam(@RequestParam String payload) throws Exception {
		log.info("JNDI (example1): " + payload);
		return new javax.naming.InitialContext().lookup(payload);
	}

}
