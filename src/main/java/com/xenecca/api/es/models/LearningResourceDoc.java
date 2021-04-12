package com.xenecca.api.es.models;

import static org.springframework.data.elasticsearch.annotations.FieldType.Long;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Mapping;

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
@Document(indexName = "xenecca-learning-resource", shards = 1, replicas = 2)
@Mapping(mappingPath = "/elastic-mappings/learning-resource-doc-mapping.json")
public class LearningResourceDoc {

	@Id
	@Field(name = "doc_id")
	private Long _docId;

	@Field(name = "name", type = Keyword)
	private String _name;

	@Field(name = "resource", type = Text)
	private String _resource;

	@Field(name = "material_type", index = false, nullValue = "URL", type = Text)
	private String _materialType;

	@Field(name = "resource_type", index = false, nullValue = "TUTORIAL", type = Text)
	private String _resourceType;

	@Field(name = "category", type = Long)
	private Long _category;

}
