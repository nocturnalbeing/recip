package com.recepies.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recepies.entities.Recepie;
import com.recepies.repositories.RecepieRepository;

@Repository
public class HomePageDAO {
	@Autowired
	RecepieRepository recepieRepository;

	public Iterable<Recepie> getAllRecepies() {
		return recepieRepository.findAll();

	}

	public Recepie publishRecepie(Recepie recepieEntity) {
		Recepie SavedRecepie = recepieRepository.save(recepieEntity);
		return SavedRecepie;

	}

}
