package com.josafhathz.app.ws.userservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josafhathz.app.ws.exceptions.UserServiceException;
import com.josafhathz.app.ws.model.request.UpdateUserRequestModel;
import com.josafhathz.app.ws.model.request.UserDetailRequestModel;
import com.josafhathz.app.ws.shared.Utils;
import com.josafhathz.app.ws.ui.model.response.UserRest;


@Service
public class UserService implements UserServiceI{
	
	Map<String, UserRest> users;
	Utils utils;
	
	public UserService() {}
	
	@Autowired
	public UserService(Utils utils) {
		this.utils = utils;
	}

	@Override
	public UserRest createUser(UserDetailRequestModel user) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail(user.getEmail());
		returnValue.setFirstName(user.getFirstName());
		returnValue.setLastName(user.getLastName());
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);

		if (users == null) {
			users = new HashMap<>();
		}
		
		users.put(returnValue.getUserId(), returnValue);
		
		return returnValue;
	}

	@Override
	public UserRest getUser(String userId) {
		if (!users.containsKey(userId)) {
			throw new UserServiceException("User not found");
		}
		
		return users.get(userId);
	}
	
	@Override
	public UserRest updateUser(String userId, UpdateUserRequestModel user) {
		if (users.containsKey(userId)) {
			UserRest updatedUser = users.get(userId);
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			users.put(userId, updatedUser);
			return updatedUser;
		} else {
			throw new UserServiceException("User to update not found");
		}
	}
	
	@Override
	public void deleteUser(String userId) {
		if (users.containsKey(userId)) {
			users.remove(userId);
		} else {
			throw new UserServiceException("User to delete does not exist");
		}
	}

}
