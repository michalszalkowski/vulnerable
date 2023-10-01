package com.michalszalkowski.vulnerable.module;

import com.michalszalkowski.vulnerable.core.filter.FilterDto;
import com.michalszalkowski.vulnerable.core.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@RestController
public class CmdController {

	private static final Logger log = LoggerFactory.getLogger(CmdController.class);

	@GetMapping("/vun/cmd/example1/")
	private void cmdByQueryParam(@RequestParam String cmd) {
		log.info("CMD (example1): " + cmd);
		extracted(cmd);
	}

	@PostMapping(value = "/vun/cmd/example2/", consumes = MediaType.APPLICATION_JSON_VALUE)
	private void cmdByJsonBody(@RequestBody FilterDto filter) {
		log.info("CMD (example2): " + filter.getFilter());
		extracted(filter.getFilter());
	}

	@PostMapping(value = "/vun/cmd/example3/", consumes = MediaType.APPLICATION_XML_VALUE)
	private void cmdByXmlBody(@RequestBody FilterDto filter) {
		log.info("CMD (example3): " + filter.getFilter());
		extracted(filter.getFilter());
	}

	@GetMapping("/vun/cmd/example4/")
	private void cmdByCookie(@CookieValue String cmd) {
		log.info("CMD (example4): " + cmd);
		extracted(cmd);
	}


	private static void extracted(String cmd) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("bash", "-c", cmd);

		try {
			Process process = processBuilder.start();
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			if (process.waitFor() == 0) {
				log.info("CMD (result): " + output);
			} else {
				log.info("CMD (result error)");
			}
		} catch (Exception e) {
			log.error("Error " + e.getMessage());
		}
	}
}
