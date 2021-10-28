package com.josafhathz.app.ws.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailRequestModel {
	
	@NotNull(message = "First name cannot be missing!")
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(max = 8, min = 3, message = "Password must be 3-8 characters")
	private String password;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
