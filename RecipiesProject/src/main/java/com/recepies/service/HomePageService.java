package com.recepies.service;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.recepies.dao.HomePageDAO;
import com.recepies.model.Recepie;
import com.recepies.model.RecepieRequest;
import com.recepies.utils.RecepieUtils;

@Service
public class HomePageService {
	@Autowired
	HomePageDAO homePageDAO;

	public List<Recepie> getAllRecepies() {
		List<Recepie> recepiesList = RecepieUtils.buildRecepieModel(homePageDAO);
		return recepiesList;

	}
	@Value("${filePath}")
	private  String filePath;
	public com.recepies.entities.Recepie publishARecepie(RecepieRequest request) throws UnknownHostException {

		 String nameOftheImageInFileSystem = RecepieUtils.saveAnImage(request,filePath);
		String uri=  RecepieUtils.buildAnURI(nameOftheImageInFileSystem);
		com.recepies.entities.Recepie recepieEntity = RecepieUtils.buildRecepieEntity( uri, request);
		return homePageDAO.publishRecepie(recepieEntity);
		
	}
	
	
}
