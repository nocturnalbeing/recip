package com.recepies.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Recepie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long recepieId;
	private String name;
	private String description;
	private String ingredients;
	private String procedureOfTheRecepie;
	private Date dateOfPublishing;
	private String publishedBy;
	private String recepieURL;
	@OneToMany(orphanRemoval = true,cascade = CascadeType.ALL )
	private List<Comment> comments;
	

}
