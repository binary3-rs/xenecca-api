package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
