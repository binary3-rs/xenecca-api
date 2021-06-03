package com.xenecca.api.model.elastic;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
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
@Document(indexName = "xenecca-course", shards = 1, replicas = 2)
@Mapping(mappingPath = "/elastic-mappings/course-doc-mapping.json")
public class CourseDoc {

	@Id
	@Field(name = "doc_id")
	private Long _docId;

	@Field(name = "title", type = Keyword)
	private String _title;

	@Field(name = "headline", nullValue = "", type = Text)
	private String _headline;

	@Field(name = "category", storeNullValue = true, type = Keyword)
	private Long _category;

	@Field(name = "subcategory", storeNullValue = true, type = Keyword)
	private Long _subcategory;

	@Field(name = "language", storeNullValue = true, type = Keyword)
	private Long _language;

	@Field(name = "poster", index = false, storeNullValue = true, type = Text)
	private String _poster;

	@Field(name = "slug", index = false, storeNullValue = false, type = Text)
	private String _slug;

	@Field(name = "original_poster_url", index = false, storeNullValue = true, type = Text)
	private String _originalPosterUrl;

	@Field(name = "updated_at", type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date _updatedAt;

}
