package com.recepies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recepies.entities.Recepie;

@Repository
public interface RecepieRepository extends CrudRepository<Recepie, Long> {

}
