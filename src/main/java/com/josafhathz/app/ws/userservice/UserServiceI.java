package com.josafhathz.app.ws.userservice;

import com.josafhathz.app.ws.model.request.UpdateUserRequestModel;
import com.josafhathz.app.ws.model.request.UserDetailRequestModel;
import com.josafhathz.app.ws.ui.model.response.UserRest;

public interface UserServiceI {
	UserRest createUser(UserDetailRequestModel user);
	UserRest getUser(String userId);
	UserRest updateUser(String userId, UpdateUserRequestModel user);
	void deleteUser(String userId);
}
