package com.recepies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recepies.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
User findByEmail(String email);
}
