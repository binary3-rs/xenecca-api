package com.xenecca.api.dto.response;

import com.xenecca.api.es.models.InstructorDoc;
import com.xenecca.api.model.Instructor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {
	private String _fullName;
	private String _image;

	public InstructorDTO(Instructor instructor) {
		_fullName = instructor.getFullName();
		_image = instructor.getImagePath();
	}
	
	
	public InstructorDTO(InstructorDoc instructor) {
		_fullName = instructor.getFullName();
		_image = instructor.getImage();
	}
	

	
	

}
