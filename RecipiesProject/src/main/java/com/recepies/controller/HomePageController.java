package com.recepies.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recepies.model.Recepie;
import com.recepies.model.RecepieRequest;
import com.recepies.service.HomePageService;
import com.recepies.utils.RecepieUtils;

@RestController
@RequestMapping(path = "/api/recepies/")
public class HomePageController {

	@Autowired
	HomePageService homePageService;
	@Value("${filePath}")
	private String filePath;

	@GetMapping(path = { "/", "/home" })
	public List<Recepie> getAllRecepies() {
		return homePageService.getAllRecepies();
	}

	@PostMapping(path = { "/" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public com.recepies.entities.Recepie publishARecipie(@ModelAttribute RecepieRequest recepieRequest) {
		// System.out.println("image is"+recepieRequest.getImageOfTheRecepie());
		try {
			return homePageService.publishARecepie(recepieRequest);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new com.recepies.entities.Recepie();
	}

	@GetMapping(path = "{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] m(@PathVariable("imageName") String imageName) throws IOException {
		System.out.println("entered....!!!!");
		System.out.println("value is " + filePath + imageName);
		byte[] data = RecepieUtils.ImageFetch(imageName, filePath);
		return data;
	}

}
