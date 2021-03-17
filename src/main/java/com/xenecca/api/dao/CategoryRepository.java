package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
