package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.response.CategoryDTO;
import com.xenecca.api.model.Category;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
	CategoryDTO mapToDTO(Category category);

	List<CategoryDTO> mapToDTOList(Iterable<Category> categories);
}
