package com.recepies.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long likeId;
	private Long likedOnRecepie;
	private String  emailOfLikedUser;

}
