package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.response.LanguageDTO;
import com.xenecca.api.model.course.Language;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

	LanguageDTO mapToDTO(Language language);

	List<LanguageDTO> mapToDTOList(Iterable<Language> languages);
}
