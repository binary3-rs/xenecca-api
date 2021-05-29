package com.xenecca.api.dto.response;

import com.xenecca.api.model.course.Subcategory;

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
public class SubcategoryDTO {
	private Long _id;
	private String _name;

	public SubcategoryDTO(Subcategory subcategory) {
		if (subcategory != null) {
			_id = subcategory.getId();
			_name = subcategory.getName();
		}
	}
}
