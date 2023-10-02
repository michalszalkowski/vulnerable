package com.michalszalkowski.vulnerable.module;

import com.michalszalkowski.vulnerable.core.filter.FilterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class CmdController {

	private static final Logger log = LoggerFactory.getLogger(CmdController.class);

	@GetMapping("/vun/cmd/example1/")
	private void cmdByQueryParam(@RequestParam String cmd) {
		execute("example1", cmd);
	}

	@PostMapping(value = "/vun/cmd/example2/", consumes = MediaType.APPLICATION_JSON_VALUE)
	private void cmdByJsonBody(@RequestBody FilterDto filter) {
		execute("example2", filter.getFilter());
	}

	@PostMapping(value = "/vun/cmd/example3/", consumes = MediaType.APPLICATION_XML_VALUE)
	private void cmdByXmlBody(@RequestBody FilterDto filter) {
		execute("example3", filter.getFilter());
	}

	@GetMapping("/vun/cmd/example4/")
	private void cmdByCookie(@CookieValue String cmd) {
		execute("example4", cmd);
	}

	@GetMapping("/vun/cmd/example5/")
	private void cmdByHeader(@RequestHeader("X-Filter") String cmd) {
		execute("example5", cmd);
	}

	private static void execute(String payloadName, String cmd) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("bash", "-c", cmd);

		log.info("CMD: (" + payloadName + ") " + processBuilder.command());

		try {
			Process process = processBuilder.start();
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			if (process.waitFor() == 0) {
				log.info("CMD: (result) " + output);
			} else {
				log.info("CMD: (result error)");
			}
		} catch (Exception e) {
			log.error("Error " + e.getMessage());
		}
	}
}
