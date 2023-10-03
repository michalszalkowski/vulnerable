package com.michalszalkowski.vulnerable.module;

import com.michalszalkowski.vulnerable.core.filter.FilterDto;
import com.michalszalkowski.vulnerable.core.user.UserCSVHelper;
import com.michalszalkowski.vulnerable.core.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CmdController {

	private static final Logger log = LoggerFactory.getLogger(CmdController.class);

	@GetMapping("/vun/cmd/example1/{test-id}")
	private String payloadInQueryParam(@RequestParam String cmd, @PathVariable("test-id") String testId) throws IOException {
		return execute("example1 - " + testId, cmd);
	}

	@PostMapping(value = "/vun/cmd/example2/{test-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	private String payloadInJsonBody(@RequestBody FilterDto filter, @PathVariable("test-id") String testId) throws IOException {
		return execute("example2 - " + testId, filter.getFilter());
	}

	@PostMapping(value = "/vun/cmd/example3/{test-id}", consumes = MediaType.APPLICATION_XML_VALUE)
	private String payloadInXmlBody(@RequestBody FilterDto filter, @PathVariable("test-id") String testId) throws IOException {
		return execute("example3 - " + testId, filter.getFilter());
	}

	@GetMapping("/vun/cmd/example4/{test-id}")
	private String payloadInCookie(@CookieValue String cmd, @PathVariable("test-id") String testId) throws IOException {
		return execute("example4 - " + testId, cmd);
	}

	@GetMapping("/vun/cmd/example5/{test-id}")
	private String payloadInHeader(@RequestHeader("X-Filter") String cmd, @PathVariable("test-id") String testId) throws IOException {
		return execute("example5 - " + testId, cmd);
	}

	@PostMapping(value = "/vun/cmd/example6/{test-id}")
	private String uploadCSVFile(@RequestParam("file") MultipartFile file, @PathVariable("test-id") String testId) throws IOException {
		List<UserEntity> users = UserCSVHelper.csvToObj(file.getInputStream());
		String response = "";
		for (UserEntity user : users) {
			response += execute("example6 - " + testId, user.getSurname());
		}
		return response;
	}

	private static String execute(String payloadName, String cmd) throws IOException {
		String cmdStr = String.format("bash -c %s", cmd);
		Process process = Runtime.getRuntime().exec(cmdStr);
		log.info("CMD: (" + payloadName + ") " + cmdStr);
		return new BufferedReader(new InputStreamReader(process.getInputStream())).lines().collect(Collectors.joining());
	}

	private static String execute1(String payloadName, String cmd) throws IOException {
		ProcessBuilder process = new ProcessBuilder().command("bash", "-c", cmd);
		log.info("CMD: (" + payloadName + ") " + process.command());
		return new BufferedReader(new InputStreamReader(process.start().getInputStream())).lines().collect(Collectors.joining());
	}
}
