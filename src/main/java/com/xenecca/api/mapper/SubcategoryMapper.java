package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.response.SubcategoryDTO;
import com.xenecca.api.model.Subcategory;

@Mapper(componentModel = "spring")
public interface SubcategoryMapper {
	SubcategoryDTO mapToDTO(Subcategory subcategory);

	List<SubcategoryDTO> mapToDTOList(Iterable<Subcategory> subcategories);
}
