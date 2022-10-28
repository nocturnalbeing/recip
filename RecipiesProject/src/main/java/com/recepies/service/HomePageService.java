package com.recepies.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.recepies.dao.HomePageDAO;
import com.recepies.dao.UserDAO;
import com.recepies.entities.Recepie;
import com.recepies.entities.User;
import com.recepies.model.CommentModel;
import com.recepies.model.LikeModel;
import com.recepies.model.RecepieModel;
import com.recepies.model.RecepieRequest;
import com.recepies.utils.ImageUtils;
import com.recepies.utils.RecepieUtils;

@Service
public class HomePageService {
	@Autowired
	HomePageDAO homePageDAO;
	@Value("${filePath}")
	private String filePath;
	@Autowired
	UserDAO userDAO;
	@Value("${server.port}")
	private String port;

	public List<RecepieModel> getAllRecepies(String userEmail) {
		return homePageDAO.getAllRecepies(userEmail);

	}

	public RecepieModel publishARecepie(RecepieRequest request) throws UnknownHostException {

		String nameOftheImageInFileSystem = ImageUtils.saveAnImage(request, filePath);
		String uri = ImageUtils.buildAnURI(nameOftheImageInFileSystem, port);
		com.recepies.entities.Recepie recepieEntity = RecepieUtils.buildRecepieEntity(uri, request);
		User user = userDAO.getUser(request.getPublishedBy());
		return RecepieUtils.buildRecepieModel(homePageDAO.publishRecepie(recepieEntity), false);

	}

	public byte[] fetchImage(@PathVariable("imageName") String imageName) throws IOException {
		System.out.println("entered....!!!!");
		System.out.println("value is " + filePath + imageName);
		byte[] data = ImageUtils.ImageFetch(imageName, filePath);
		return data;
	}

	public List<CommentModel> commentOnaRecepie(CommentModel commentModel) {
		// com.recepies.entities.Comment commentEntity=
		// RecepieUtils.buildCommentEntity(comment);
		System.out.println("entered service");

		List<CommentModel> commentList = homePageDAO.commentOnARecepie(commentModel);
		System.out.println("exited service");

		return commentList;
	}

	public RecepieModel likeARecepie(LikeModel like) {
		RecepieModel recepieModel = homePageDAO.likeARecepie(like);
		User user = userDAO.getUser(like.getEmailOfLikedUser());
		System.out.println("user is " + user + "====recepie is " + recepieModel);
		return recepieModel;

	}

	public List<RecepieModel> searchARecepie(String keyword) {
		return homePageDAO.searchARecepie(keyword);
	}
}
