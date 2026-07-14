package com.example.securityApp.securityApplicationo;

import com.example.securityApp.securityApplicationo.entities.User;
import com.example.securityApp.securityApplicationo.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationoApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		User user = new User(4L, "nishikant@gmail.com", "12345", "nishikant");

		String token = jwtService.generateAccessToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);

	}

}
