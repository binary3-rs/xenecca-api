package com.xenecca.api.dto.response;

import com.xenecca.api.model.Category;

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
public class CategoryDTO {
	private Long _id;
	private String _name;

	public CategoryDTO(Category category) {
		if (category != null) {
			_id = category.getId();
			_name = category.getName();
		}
	}
}
