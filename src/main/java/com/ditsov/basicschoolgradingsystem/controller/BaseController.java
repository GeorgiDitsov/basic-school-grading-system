package com.ditsov.basicschoolgradingsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ditsov.basicschoolgradingsystem.service.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BaseController {

    	private final UserService userService;

    	@PostMapping("/login")
    	public String login(@RequestParam final String username, @RequestParam final String password) {
    	    	return userService.attemptLogin(username, password);
    	}

}
