package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.LanguageRepository;
import com.xenecca.api.dto.request.NewLanguageDTO;
import com.xenecca.api.model.course.Language;
import com.xenecca.api.service.LanguageService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository _languageRepository;

	@Override
	public Language addLanguage(NewLanguageDTO languageData) {
		Language language = Language.builder().name(languageData.getName()).build();
		return getLanguageRepository().save(language);
	}

	@Override
	public Iterable<Language> getAllLanguages() {
		return getLanguageRepository().findAll();
	}

}
