package com.michalszalkowski.vulnerable.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class CmdController {

	private static final Logger log = LoggerFactory.getLogger(CmdController.class);

	@GetMapping("/vun/cmd/example1/")
	private void cmdByQueryParam(@RequestParam String cmd) {
		log.info("CMD (example1): " + cmd);
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
				log.info("CMD (example1 result): " + output);
			} else {
				log.info("CMD (example1 result error)");
			}
		} catch (Exception e) {
			log.error("Error " + e.getMessage());
		}
	}
}
