package com.recepies.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class RecepieModel { 

	private Long recepieId;
	private String name;
	private String description;
	private String ingredients;
	private String procedureOfTheRecepie;
	private Date dateOfPublishing;
	private String publishedBy;
	private String recepieURL;
	private List<CommentModel> commentModels;
	private boolean wheatherLikedByuser;

}
