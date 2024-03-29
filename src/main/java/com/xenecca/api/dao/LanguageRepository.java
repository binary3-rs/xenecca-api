package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.course.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {

}
