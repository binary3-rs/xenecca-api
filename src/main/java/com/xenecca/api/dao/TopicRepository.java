package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

}
