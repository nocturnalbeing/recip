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
import org.springframework.web.bind.annotation.RestController;

import com.recepies.model.CommentModel;
import com.recepies.model.LikeModel;
import com.recepies.model.RecepieModel;
import com.recepies.model.RecepieRequest;
import com.recepies.model.SearchRequest;
import com.recepies.service.HomePageService;

@RestController
@RequestMapping(path = "/api/recepies")
public class HomePageController {

	@Autowired
	HomePageService homePageService;
	

	@GetMapping(path = { "/{userEmail}", "/home/{userEmail}" })
	public List<RecepieModel> getAllRecepies(@PathVariable final String userEmail) {
		System.out.println("userEmail is "+userEmail);
		return homePageService.getAllRecepies(userEmail);
	}
	@PostMapping(path = "/home/search/" )
	public List<RecepieModel> searchARecepie(@RequestBody  SearchRequest searchRequest) {
		System.out.println("key word of the recepie is "+searchRequest.getKeyword());
		return homePageService.searchARecepie(searchRequest.getKeyword());
	}
	@GetMapping(path =  "/")
	public List<RecepieModel> getAllRecepiesWithNoEmail() {
		return homePageService.getAllRecepies(null);
	}

	@PostMapping(path = { "/" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RecepieModel publishARecipie(@ModelAttribute RecepieRequest recepieRequest) {
		// System.out.println("image is"+recepieRequest.getImageOfTheRecepie());
		try {
			return homePageService.publishARecepie(recepieRequest);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new RecepieModel();
	}

	@GetMapping(path = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] fetchImage(@PathVariable("imageName") String imageName) throws IOException {
		System.out.println("for image");
		return homePageService.fetchImage(imageName);
	}
	@PostMapping(path = "/comment")
	public List<CommentModel> commentOnRecepie(@RequestBody CommentModel commentModel){
		System.out.println("entered controller");
		System.out.println(commentModel);
		List<CommentModel> commentList=homePageService.commentOnaRecepie(commentModel);
		System.out.println("left controller");

		return commentList;
		
	}
	@PostMapping(path="/likeARecepie")
	public RecepieModel likeArecepie(@RequestBody LikeModel like) {
		System.out.println(like);
		RecepieModel model=homePageService.likeARecepie(like);
		return model;
		
	}

}
