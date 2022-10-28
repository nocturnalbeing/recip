package com.recepies.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
 public class RecepieRequest {

	private String name;
	private String description;
	private String ingredients;
	private String procedureOfTheRecepie;
	private String publishedBy;
	private MultipartFile imageOfTheRecepie;

}
