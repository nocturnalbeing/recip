package com.recepies.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recepies.entities.Recepie;
import com.recepies.model.RecepieModel;

@Repository
public interface RecepieRepository extends CrudRepository<Recepie, Long> {
	List<Recepie> findAllByPublishedBy(String publishedBy);

	List<Recepie> findByNameContaining(@Param("keyword")String keyword);

}
