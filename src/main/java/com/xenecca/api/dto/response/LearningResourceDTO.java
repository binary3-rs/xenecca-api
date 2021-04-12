package com.xenecca.api.dto.response;

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
public class LearningResourceDTO {

	private Long _id;

	private String _name;

	private String _resource;

	private String _materialType;

	private String _resourceType;

}
