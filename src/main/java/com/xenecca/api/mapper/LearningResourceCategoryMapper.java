package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.request.NewLearningResourceCategoryDTO;
import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.dto.response.LearningResourceCategoryDTO;
import com.xenecca.api.model.learnresource.LearningResourceCategory;

@Mapper(componentModel = "spring")

public interface LearningResourceCategoryMapper {

	public LearningResourceCategoryDTO mapToDTO(LearningResourceCategory resourceCategory);
	public List<LearningResourceCategoryDTO> mapToDTOList(Iterable<LearningResourceCategory> resourceCategories);
	public LearningResourceCategory mapToEntity(NewLearningResourceCategoryDTO resourceCategory);
}
