package com.josafhathz.app.ws.ui.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josafhathz.app.ws.model.request.UpdateUserRequestModel;
import com.josafhathz.app.ws.model.request.UserDetailRequestModel;
import com.josafhathz.app.ws.ui.model.response.UserRest;
import com.josafhathz.app.ws.userservice.UserServiceI;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserServiceI userService;
	
	@GetMapping
	public String getUsers(
			@RequestParam(value = "page", defaultValue = "1" , required = false) int page,
			@RequestParam(value = "limit", required = true, defaultValue="5") int limit
		) {
		return "Users page "+page+" limit of "+limit;
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		UserRest returnValue = userService.getUser(userId);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<UserRest> createUser(@Validated @RequestBody UserDetailRequestModel user) {
		
		UserRest returnValue = userService.createUser(user);
		
		return new ResponseEntity<UserRest> (
				returnValue, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserRest> updateUser(@PathVariable(name = "userId") String userId,
			@RequestBody UpdateUserRequestModel user ) {
		
		UserRest returnValue = userService.updateUser(userId, user);
		
		return new ResponseEntity<UserRest> (
				returnValue, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") String userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
	}
}
