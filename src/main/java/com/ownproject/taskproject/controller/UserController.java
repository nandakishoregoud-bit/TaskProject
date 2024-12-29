package com.ownproject.taskproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ownproject.taskproject.payload.UserDto;
import com.ownproject.taskproject.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		
		UserDto saved = userService.createUser(userDto);
		
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}

}
