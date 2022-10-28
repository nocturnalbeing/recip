package com.recepies.service;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recepies.dao.UserDAO;
import com.recepies.model.User;
import com.recepies.model.UserLoginRequest;
import com.recepies.model.UserModel;
import com.recepies.model.UserRegisterRequest;

@Service 
public class UserService {
	@Autowired
	UserDAO userDAO;
	public String registerAnUser(UserRegisterRequest request) throws UnknownHostException {
		return userDAO.registerAnUser(request);
		
	}
	public User signInAnUser(UserLoginRequest request) {
		return userDAO.signInAnUser(request);
	}
}
