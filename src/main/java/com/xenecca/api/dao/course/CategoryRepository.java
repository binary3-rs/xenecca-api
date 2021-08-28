package com.xenecca.api.dao.course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.course.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
