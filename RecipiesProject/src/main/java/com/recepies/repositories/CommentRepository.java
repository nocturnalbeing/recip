package com.recepies.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.recepies.entities.Comment;
import com.recepies.entities.Recepie;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByRecepie(Recepie recepie);

}
