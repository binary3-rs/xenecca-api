package com.xenecca.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.LanguageRepository;
import com.xenecca.api.dto.request.NewLanguageDTO;
import com.xenecca.api.model.Language;
import com.xenecca.api.service.LanguageService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
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
	@Cacheable(cacheNames = "languages")
	public Iterable<Language> getAllLanguages() {
		return getLanguageRepository().findAll();
	}

}
