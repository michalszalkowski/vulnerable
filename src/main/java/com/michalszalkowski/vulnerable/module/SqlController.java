package com.michalszalkowski.vulnerable.module;

import com.michalszalkowski.vulnerable.core.filter.FilterDto;
import com.michalszalkowski.vulnerable.core.user.UserCSVHelper;
import com.michalszalkowski.vulnerable.core.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SqlController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger log = LoggerFactory.getLogger(SqlController.class);

	@GetMapping("/vun/sql/example1/{test-id}")
	private List<UserEntity> payloadInQueryParam(@RequestParam String name, @PathVariable("test-id") String testId) {
		String sql = "select * from users  where name='" + name + "'";
		log.info("SQL (example1 - " + testId + "):" + sql);
		try {
			return jdbcTemplate.query(
					sql,
					getMapper()
			);
		} catch (Exception e) {
			return List.of();
		}
	}

	@PostMapping(value = "/vun/sql/example2/", consumes = MediaType.APPLICATION_JSON_VALUE)
	private List<UserEntity> payloadInJsonBody(@RequestBody FilterDto filter) {
		String sql = "select * from users  where name='" + filter.getFilter() + "'";
		log.info("SQL (example2): " + sql);
		return jdbcTemplate.query(
				sql,
				getMapper()
		);
	}

	@PostMapping("/vun/sql/example3/")
	void newUser(@RequestBody UserEntity userEntity) {
		String sql = "INSERT INTO users(name, surname) VALUES ('" + userEntity.getName() + "','" + userEntity.getSurname() + "')";
		log.info("SQL (example3): " + sql);
		jdbcTemplate.execute(sql);
	}

	@GetMapping("/vun/sql/example4/")
	private List<UserEntity> payloadInCookie(@CookieValue String name) {
		String sql = "select * from users  where name='" + name + "'";
		log.info("SQL (example4):" + sql);
		return jdbcTemplate.query(
				sql,
				getMapper()
		);
	}

	@GetMapping("/vun/sql/example5/{test-id}")
	private List<UserEntity> payloadInHeader(@RequestHeader("X-Filter") String name, @PathVariable("test-id") String testId) {
		String sql = "select * from users  where name='" + name + "'";
		log.info("SQL (example5 - " + testId + "):" + sql);
		return jdbcTemplate.query(
				sql,
				getMapper()
		);
	}

	@PostMapping(value = "/vun/sql/example6/", consumes = MediaType.APPLICATION_XML_VALUE)
	private List<UserEntity> payloadInXmlBody(@RequestBody FilterDto filter) {
		String sql = "select * from users  where name='" + filter.getFilter() + "'";
		log.info("SQL (example6): " + sql);
		return jdbcTemplate.query(
				sql,
				getMapper()
		);
	}

	@PostMapping(value = "/vun/sql/example7/{test-id}")
	private List<UserEntity> uploadCSVFile(@RequestParam("file") MultipartFile file, @PathVariable("test-id") String testId) throws IOException {
		List<UserEntity> users = UserCSVHelper.csvToObj(file.getInputStream());
		for (UserEntity user : users) {
			String sql = "INSERT INTO users(name, surname) VALUES ('" + user.getName() + "','" + user.getSurname() + "')";
			log.info("SQL (example7 - " + testId + "):" + sql);
			jdbcTemplate.execute(sql);
		}
		return users;
	}

	private RowMapper<UserEntity> getMapper() {
		return (paramResultSet, paramInt) -> {
			UserEntity user = new UserEntity();
			user.setId(paramResultSet.getInt("id"));
			user.setName(paramResultSet.getString("name"));
			user.setSurname(paramResultSet.getString("surname"));
			return user;
		};
	}
}
