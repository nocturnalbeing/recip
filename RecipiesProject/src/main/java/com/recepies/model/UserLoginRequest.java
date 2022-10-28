package com.recepies.model;

import lombok.Data;

@Data
public class UserLoginRequest {
	private String email;
	private String password;

}
