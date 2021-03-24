package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.response.CourseDTO;
import com.xenecca.api.model.Course;

@Mapper(componentModel = "spring")

public interface CourseMapper {
	CourseDTO mapToDTO(Course course);

	List<CourseDTO> mapToDTOList(Iterable<Course> courses);
}
