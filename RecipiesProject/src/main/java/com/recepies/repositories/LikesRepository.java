package com.recepies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recepies.entities.Likes;

public interface LikesRepository extends CrudRepository<Likes, Long> {
	Likes findByLikedOnRecepieAndEmailOfLikedUser(Long recepieId,String email);
}