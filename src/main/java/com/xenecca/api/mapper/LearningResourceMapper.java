package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.xenecca.api.dto.response.LearningResourceDTO;
import com.xenecca.api.es.models.LearningResourceDoc;
import com.xenecca.api.model.learnresource.LearningResource;

@Mapper(componentModel = "spring")

public interface LearningResourceMapper {
	@Mappings({ @Mapping(target = "materialType", source = "resource.materialType"),
			@Mapping(target = "resourceType", expression = "java(resource.getResourceType().getName())") })
	LearningResourceDTO mapToDTO(LearningResource resource);

	@Mappings({ @Mapping(target = "id", source = "doc.docId") })
	LearningResourceDTO mapDocToDTO(LearningResourceDoc doc);

	List<LearningResourceDTO> mapToDTOList(Iterable<LearningResource> resources);

	List<LearningResourceDTO> mapDocsToDTOList(List<LearningResourceDoc> resources);
}
