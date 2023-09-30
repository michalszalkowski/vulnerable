package com.michalszalkowski.vulnerable.module.sqli;

import com.michalszalkowski.vulnerable.core.filter.FilterDto;
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

	@GetMapping("/vun/sql/example1/api/users")
	private List<UserEntity> list(@RequestParam String name) {
		String sql = "select * from users  where name='" + name + "'";
		log.info("SQL (example1): " + sql);
		return jdbcTemplate.query(
				sql,
				getMapper()
		);
	}

	@PostMapping("/vun/sql/example2/api/users")
	private List<UserEntity> listByFilterBody(@RequestBody FilterDto filter) {
		String sql = "select * from users  where name='" + filter.getFilter() + "'";
		log.info("SQL (example2): " + sql);
		return jdbcTemplate.query(
				sql,
				getMapper()
		);
	}

	@PostMapping("/vun/sql/example3/api/users")
	void newUser(@RequestBody UserEntity userEntity) {
		String sql = "INSERT INTO users(name, surname) VALUES ('" + userEntity.getName() + "','" + userEntity.getSurname() + "')";
		log.info("SQL (example3): " + sql);
		jdbcTemplate.execute(sql);
	}

	@GetMapping("/vun/sql/example4/api/users")
	private List<UserEntity> listByFilterCookie(@CookieValue String name) {
		String sql = "select * from users  where name='" + name + "'";
		log.info("SQL (example4):" + sql);
		return jdbcTemplate.query(
				sql,
				getMapper()
		);
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
