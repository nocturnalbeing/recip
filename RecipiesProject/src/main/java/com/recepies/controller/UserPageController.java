package com.recepies.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recepies.model.CommentModel;
import com.recepies.model.LikeModel;
import com.recepies.model.RecepieModel;
import com.recepies.model.RecepieRequest;
import com.recepies.model.User;
import com.recepies.model.UserLoginRequest;
import com.recepies.model.UserModel;
import com.recepies.model.UserRegisterRequest;
import com.recepies.service.HomePageService;
import com.recepies.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserPageController {
	@Autowired
	UserService userService;

	@PostMapping(path = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String registerAnUser(UserRegisterRequest request) throws UnknownHostException {
		return userService.registerAnUser(request);

	}
	@PostMapping(path = "/signin")
	public User signingInAnUser(@RequestBody  UserLoginRequest request) throws UnknownHostException {
		return userService.signInAnUser(request);

	}
}
