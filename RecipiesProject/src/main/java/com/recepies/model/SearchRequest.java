package com.recepies.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchRequest {
private String keyword;
private String emailOftheLoggedUser;
}
