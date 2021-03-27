package com.xenecca.api.es.models;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;

import org.springframework.data.elasticsearch.annotations.Field;
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
public class InstructorDoc {
	@Field(type = Keyword)
	private String _name;

	@Field(type = Text, storeNullValue = true)
	private String _image;
}
