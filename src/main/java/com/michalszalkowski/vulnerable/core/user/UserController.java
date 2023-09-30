package com.michalszalkowski.vulnerable.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/api/users")
	private List<UserEntity> list() {
		return userRepository.findAll();
	}
}
