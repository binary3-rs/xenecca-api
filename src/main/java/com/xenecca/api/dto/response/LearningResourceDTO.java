package com.xenecca.api.dto.response;

import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.dto.request.NewLearningResourceDTO.NewLearningResourceDTOBuilder;
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
public class LearningResourceDTO {

	private Long _id;

	private String _name;

	private String _resource;

	private String _materialType;

	private String _resourceType;

}
