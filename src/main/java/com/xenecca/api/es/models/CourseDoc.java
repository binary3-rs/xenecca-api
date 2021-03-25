package com.xenecca.api.es.models;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;
import static org.springframework.data.elasticsearch.annotations.FieldType.Double;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "courses", replicas = 2)
public class CourseDoc {
	
	@Id
	private Long docId;

	@Field(type = Keyword, fielddata = true)
	private String title;

	@Field(type = Text)
	private String headline;

	@Field(type = Double)
	private Double avgRating;

	@Field(type = Keyword, fielddata = true)
	private Long category;

	@Field(type = Keyword, fielddata = true)
	private Long subcategory;

	@Field(type = Keyword, fielddata = true)
	private Long topic;

	@Field(type = Keyword, fielddata = true)
	private Long language;

	@Field(type = Double)
	private Double price;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<InstructorDoc> instructors;
}
