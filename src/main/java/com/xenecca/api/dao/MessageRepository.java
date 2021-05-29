package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
