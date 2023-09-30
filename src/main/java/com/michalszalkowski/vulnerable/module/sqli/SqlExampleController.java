package com.michalszalkowski.vulnerable.module.sqli;

import com.michalszalkowski.vulnerable.core.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SqlExampleController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger log = LoggerFactory.getLogger(SqlExampleController.class);

	@GetMapping("/vun/api/users")
	private List<UserEntity> list(@RequestParam String name) {
		return jdbcTemplate.query(
				"select * from users  where name='" + name + "'",
				getMapper()
		);
	}

	@PostMapping("/vun/api/users")
	void newEmployee(@RequestBody UserEntity userEntity) {
		jdbcTemplate.execute("INSERT INTO users(name, surname) VALUES ('" + userEntity.getName() + "','" + userEntity.getSurname() + "')");
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
