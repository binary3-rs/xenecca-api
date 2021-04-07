package com.xenecca.api.dto.request;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.xenecca.api.model.type.MaterialType;
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
	private MaterialType _materialType = MaterialType.URL;

	@Builder.Default
	private ResourceType _resourceType = ResourceType.TUTORIAL;

	private Long _resourceCategoryId;
	
}
