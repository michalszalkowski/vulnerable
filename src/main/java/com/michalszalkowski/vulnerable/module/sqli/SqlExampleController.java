package com.michalszalkowski.vulnerable.module.sqli;

import com.michalszalkowski.vulnerable.core.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SqlExampleController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/vun/api/users")
	private List<UserEntity> list(@RequestParam String name) {

		String str = "select * from users  where name='" + name + "'";

		return jdbcTemplate.query(str, getMapper());
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
