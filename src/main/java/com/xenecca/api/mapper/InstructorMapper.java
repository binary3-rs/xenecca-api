package com.xenecca.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.xenecca.api.dto.response.InstructorDTO;
import com.xenecca.api.es.models.InstructorDoc;
import com.xenecca.api.model.Instructor;

public class InstructorMapper {

	public List<InstructorDTO> mapDocsToDTOList(List<InstructorDoc> docs) {
		List<InstructorDTO> instructors = new ArrayList<InstructorDTO>();
		docs.forEach(doc -> instructors.add(new InstructorDTO(doc)));
		return instructors;
	}

	public List<InstructorDTO> mapToDTOList(Set<Instructor> instructors) {
		List<InstructorDTO> instructorsDTO = new ArrayList<InstructorDTO>();
		instructors.forEach(instructor -> instructorsDTO.add(new InstructorDTO(instructor)));
		return instructorsDTO;
	}

}