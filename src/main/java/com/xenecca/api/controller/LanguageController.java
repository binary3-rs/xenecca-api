package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewLanguageDTO;
import com.xenecca.api.dto.response.LanguageDTO;
import com.xenecca.api.mapper.LanguageMapper;
import com.xenecca.api.model.course.Language;
import com.xenecca.api.service.LanguageService;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
@RequestMapping("/api/v1/languages/")
public class LanguageController {

	@Autowired
	private LanguageService _languageService;

	@Autowired
	private LanguageMapper _languageMapper;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@ApiOperation(value = "Add new language")
	public LanguageDTO addCategory(@RequestBody NewLanguageDTO data) {
		return getLanguageMapper().mapToDTO(getLanguageService().addLanguage(data));

	}

	@GetMapping
	@ApiOperation(value = "Get all languages")
	public List<LanguageDTO> getAllLanguages() {
		Iterable<Language> languages = getLanguageService().getAllLanguages();
		return getLanguageMapper().mapToDTOList(languages);

	}
}
