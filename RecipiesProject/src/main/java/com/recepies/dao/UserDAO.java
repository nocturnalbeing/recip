package com.recepies.dao;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.recepies.entities.User;
import com.recepies.model.UserLoginRequest;
import com.recepies.model.UserModel;
import com.recepies.model.UserRegisterRequest;
import com.recepies.repositories.UserRepository;
import com.recepies.utils.ImageUtils;
import com.recepies.utils.UserUtil;

@Repository
public class UserDAO {
	@Autowired
	UserRepository userRepository;
	@Value("${filePath}")
	String filePath;
	@Value("${server.port}")
	String port;

	public User getUser(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

public String registerAnUser(UserRegisterRequest request) throws UnknownHostException {
	User existingUser=userRepository.findByEmail(request.getEmail());
	if(existingUser!=null) {
		throw new RuntimeException("user already exists");
	}
	User user=new User();
	user.setEmail(request.getEmail());
	user.setFirstName(request.getFirstName());
	user.setLastName(request.getLastName());
	String fileName = ImageUtils.saveAnImage(request, filePath);
	String uri=ImageUtils.buildAnURI(fileName, port);
	user.setProfilePicturePath(uri);
	user.setUserName(request.getUserName());
	user.setPassword(request.getPassword());
	User registredUser = userRepository.save(user);
	return "registration successful";
}

public com.recepies.model.User signInAnUser(UserLoginRequest request) {

	User user=userRepository.findByEmail(request.getEmail());
	if(user.getPassword().equals(request.getPassword())) {
		return UserUtil.buildAnUserModel(user);
	}
	
	return null;
}

}
