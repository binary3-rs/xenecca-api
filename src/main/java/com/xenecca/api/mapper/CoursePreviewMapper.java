package com.xenecca.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.xenecca.api.dto.response.CoursePreviewDTO;
import com.xenecca.api.es.models.CourseDoc;
import com.xenecca.api.model.Course;

@Component
public class CoursePreviewMapper {

	public CoursePreviewDTO mapDocToDTO(CourseDoc doc) {
		return new CoursePreviewDTO(doc);
	}

	public List<CoursePreviewDTO> mapDocToDTOList(List<CourseDoc> docs) {
		List<CoursePreviewDTO> coursePreviews = new ArrayList<CoursePreviewDTO>();
		docs.forEach(doc -> coursePreviews.add(new CoursePreviewDTO(doc)));
		return coursePreviews;
	}

	public List<CoursePreviewDTO> mapCoursesToDTOList(Iterable<Course> courses) {
		List<CoursePreviewDTO> coursePreviews = new ArrayList<CoursePreviewDTO>();
		courses.forEach(course -> coursePreviews.add(new CoursePreviewDTO(course)));
		return coursePreviews;
	}

}
