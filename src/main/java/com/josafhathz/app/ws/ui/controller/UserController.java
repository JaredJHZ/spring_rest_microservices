package com.josafhathz.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

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

import com.josafhathz.app.ws.exceptions.UserServiceException;
import com.josafhathz.app.ws.model.request.UpdateUserRequestModel;
import com.josafhathz.app.ws.model.request.UserDetailRequestModel;
import com.josafhathz.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	Map<String, UserRest> users;
	
	@GetMapping
	public String getUsers(
			@RequestParam(value = "page", defaultValue = "1" , required = false) int page,
			@RequestParam(value = "limit", required = true) int limit
		) {
		return "Users page "+page+" limit of "+limit;
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		if(true) {
			throw new UserServiceException("A user service exception is thrown");
		}
		
		if (users.containsKey(userId)) {
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRest>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<UserRest> createUser(@Validated @RequestBody UserDetailRequestModel user) {
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(user.getEmail());
		returnValue.setFirstName(user.getFirstName());
		returnValue.setLastName(user.getLastName());
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		if (users == null) {
			users = new HashMap<>();
		}
		
		users.put(returnValue.getUserId(), returnValue);
		
		return new ResponseEntity<UserRest> (
				returnValue, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserRest> updateUser(@PathVariable(name = "userId") String userId,
			@RequestBody UpdateUserRequestModel user ) {
		if (users.containsKey(userId)) {
			UserRest updatedUser = users.get(userId);
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			users.put(userId, updatedUser);
			return new ResponseEntity<UserRest>(updatedUser, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<UserRest>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") String userId) {
		if (users.containsKey(userId)) {
			users.remove(userId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
