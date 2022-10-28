package com.recepies.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegisterRequest {
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private MultipartFile profPicture;
	

}
