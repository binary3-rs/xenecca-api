package com.xenecca.api.dto.request;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.xenecca.api.model.type.LearningResourceDomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewLearningResourceCategoryDTO {
	@NotNull
	private String _name;

	@NotNull
	private LearningResourceDomain _domain;

	@Builder.Default
	private String _tags = "";

	private MultipartFile _logo;

}
