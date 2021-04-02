package com.xenecca.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.xenecca.api.dto.response.InstructorDTO;
import com.xenecca.api.es.models.InstructorDoc;
import com.xenecca.api.model.Instructor;

@Component
public class InstructorMapper {
	public static List<InstructorDTO> mapDocToDTO(List<InstructorDoc> docs) {
		List<InstructorDTO> instructors = new ArrayList<InstructorDTO>();
		docs.forEach(doc -> instructors.add(new InstructorDTO(doc)));
		return instructors;
	}

	public static List<InstructorDTO> mapToDTO(Set<Instructor> instructors) {
		List<InstructorDTO> instructorsDTO = new ArrayList<InstructorDTO>();
		instructors.forEach(instructor -> instructorsDTO.add(new InstructorDTO(instructor)));
		return instructorsDTO;
	}
}
