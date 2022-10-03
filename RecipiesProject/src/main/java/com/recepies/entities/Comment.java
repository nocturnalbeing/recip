package com.recepies.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Comment {
	@Id
private String commentId;
private String commentedContent;
private String postedBy;
private String postedOnRecepie;
}
