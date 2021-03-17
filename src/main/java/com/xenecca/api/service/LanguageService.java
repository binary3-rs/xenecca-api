package com.xenecca.api.service;

import java.util.List;

import com.xenecca.api.dto.request.NewLanguageDTO;
import com.xenecca.api.model.Language;

public interface LanguageService {

	public Language addLanguage(NewLanguageDTO languageData);

	public Iterable<Language> getAllLanguages();

}
