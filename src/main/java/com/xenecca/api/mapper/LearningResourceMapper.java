package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.response.LearningResourceDTO;
import com.xenecca.api.model.learnresource.LearningResource;

@Mapper(componentModel = "spring")

public interface LearningResourceMapper {
	LearningResourceDTO mapToDTO(LearningResource resource);

	List<LearningResourceDTO> mapToDTOList(Iterable<LearningResource> resources);
}
