package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

	@RequestMapping("/")
	String principal() {
		return "Olá Spring Boot App.";
	}
}