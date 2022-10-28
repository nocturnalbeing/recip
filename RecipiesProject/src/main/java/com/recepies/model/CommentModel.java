package com.recepies.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentModel {

private String commentedContent;
private String postedBy;
private Long postedOnRecepie;
}
