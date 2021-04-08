package com.xenecca.api.es.models;

import static org.springframework.data.elasticsearch.annotations.FieldType.Double;
import static org.springframework.data.elasticsearch.annotations.FieldType.Integer;
import static org.springframework.data.elasticsearch.annotations.FieldType.Float;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import java.util.Date;
import java.util.List;

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

	@Field(name = "avg_rating", index = false, nullValue = "0.0", type = Double)
	private Double _avgRating;

	@Field(name = "category", storeNullValue = true, type = Keyword)
	private Long _category;

	@Field(name = "subcategory", storeNullValue = true, type = Keyword)
	private Long _subcategory;

	@Field(name = "topic", storeNullValue = true, type = Keyword)
	private Long _topic;

	@Field(name = "language", storeNullValue = true, type = Keyword)
	private Long _language;

	@Field(name = "price", type = Double)
	private Double _price;

	@Field(name = "old_price", type = Double)
	private Double _oldPrice;

	@Field(name = "price_as_string", index = false, type = Text)
	private String _priceAsString;

	@Field(name = "badge", type = Text)
	private String _badge;

	@Field(name = "instructors", type = FieldType.Nested, includeInParent = true)
	private List<InstructorDoc> _instructors;

	@Field(name = "poster", index = false, storeNullValue = true, type = Text)
	private String _poster;

	@Field(name = "original_poster_url", index = false, storeNullValue = true, type = Text)
	private String _originalPosterURL;

	@Field(name = "num_of_students", nullValue = "0", index = false, type = Integer)
	private Integer _numOfStudents;

	@Field(name = "num_of_reviews", nullValue = "0", index = false, type = Integer)
	private Integer _numOfReviews;

	@Field(name = "duration_in_hrs", nullValue = "0", index = false, type = Float)
	private Float _durationInHrs;

	@Field(name = "updated_at", type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date _updatedAt;

}
