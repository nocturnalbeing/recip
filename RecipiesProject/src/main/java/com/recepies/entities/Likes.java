package com.recepies.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Likes {
	@Id
	private Long likeId;
	private Long likedOnRecepie;
	private String  emailOfLikedUser;

}
