package com.recepies.utils;

import com.recepies.model.User;

public class UserUtil {
public static User buildAnUserModel(com.recepies.entities.User user) {
	User userModel=new User();
	userModel.setFirstName(user.getFirstName());
	userModel.setLastName(user.getLastName());
	userModel.setProfilePicturePath(user.getProfilePicturePath());
	userModel.setUserName(user.getUserName());
	userModel.setEmail(user.getEmail());
	return userModel;
}
}
