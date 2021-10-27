package com.josafhathz.app.ws.ui.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josafhathz.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	@GetMapping
	public String getUsers(
			@RequestParam(value = "page", defaultValue = "1" , required = false) int page,
			@RequestParam(value = "limit", required = true) int limit
		) {
		return "Users page "+page+" limit of "+limit;
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail("asdasd@asdasd.com");
		returnValue.setFirstName("oa");
		returnValue.setLastName("ao");
		return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK);
		
	}
	
	@PostMapping
	public String createUser() {
		return "Create was called!";
	}
	
	@PutMapping
	public String updateUser() {
		return "Update user was called";
	}
	
	public String deleteUser() {
		return "Delete was called";
	}
}
