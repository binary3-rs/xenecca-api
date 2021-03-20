package com.xenecca.api.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public Optional<User> findBy_username(String username);
}
