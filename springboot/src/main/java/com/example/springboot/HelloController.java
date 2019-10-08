package com.example.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@RestController
public class HelloController {
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring Boot!";
	}
}