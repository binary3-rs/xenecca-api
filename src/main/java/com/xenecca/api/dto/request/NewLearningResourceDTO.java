package com.xenecca.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.xenecca.api.model.type.ResourceType;

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
public class NewLearningResourceDTO {

	@NotBlank(message = "Name of the learning resource must not be blank!")
	private String _name;

	private String _resourceURL;

	private MultipartFile _file;

	@Builder.Default
	private ResourceType _resourceType = ResourceType.TUTORIAL_OR_COURSE;

	@NotNull(message = "You must provide category of the resource!")
	private Long _resourceCategoryId;

}
