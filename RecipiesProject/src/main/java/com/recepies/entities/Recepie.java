package com.recepies.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Recepie {
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private Long recepieId;
	private String name;
	private String description;
	private String ingredients;
	private String procedureOfTheRecepie;
	private Date dateOfPublishing;
	private String publishedBy;
	private String recepieURL;

	

}
