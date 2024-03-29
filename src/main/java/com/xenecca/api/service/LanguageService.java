package com.xenecca.api.service;

import com.xenecca.api.dto.request.NewLanguageDTO;
import com.xenecca.api.model.course.Language;

public interface LanguageService {

	public Language addLanguage(NewLanguageDTO languageData);

	public Iterable<Language> getAllLanguages();

}
