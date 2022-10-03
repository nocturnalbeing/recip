package com.recepies.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	private Long userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String profilePicturePath;

}
