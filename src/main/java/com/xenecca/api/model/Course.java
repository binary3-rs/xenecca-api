package com.xenecca.api.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

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

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3783958850800043842L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@Min(value = 0, message = "Rating must not less than 0!")
	@Max(value = 5, message = "Rating must not greater than 5!")
	@Column(name = "avg_rating", columnDefinition = "decimal(6,5) default 0.0")
	private Double _avgRating;

	@Column(name = "badge")
	private String _badge;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category _category;

	@Column(name = "has_certificate")
	private Boolean _certificate;

	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp _createdAt;

	@Column(name = "currency", columnDefinition = "varchar(1) default '$'")
	private Character _currency;

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@Builder.Default
	@OneToMany(mappedBy = "_course", orphanRemoval = true)
	private Set<CurriculumItem> _curriculumItems = new HashSet<CurriculumItem>();

	@Size(min = 0, max = 24000)
	@Column(name = "description")
	private String _description;

	@Column(name = "device_access", length = 40)
	private String _deviceAccess;

	@Min(value = 0, message = "Discount percent must not be less than 0%")
	@Max(value = 100, message = "Discount percent must not be greater than 100%")
	@Column(name = "discount_percent", columnDefinition = "integer default 0")
	private Integer _discountPercent;

	@Column(name = "discount_period", length = 40)
	private String _discountPeriod;

	@Column(name = "discount_code", length = 40)
	private String _discountCode;

	@Builder.Default
	@Column(name = "duration_in_mins", columnDefinition = "integer default 0")
	private Integer _durationInMins = 0;

	@Size(min = 0, max = 1000)
	@Column(name = "goals")
	private String _goals;

	@Column(name = "headline", length = 255)
	private String _headline;

	@Column(name = "image_path")
	private String _imagePath;

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@Builder.Default
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "course_instructor", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "instructor_id"))
	private Set<Instructor> _instructors = new HashSet<>();

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id")
	private Language _language;

	@Column(name = "has_lifetime_access")
	private Boolean _lifetimeAccess;

	@Min(1)
	@Builder.Default
	@Column(name = "num_of_articles", columnDefinition = "integer default 1")
	private Integer _numOfArticles = 1;

	@Builder.Default
	@Column(name = "num_of_reviews", columnDefinition = "integer default 0")
	private Integer _numOfReviews = 0;

	@URL
	@Column(name = "original_image_url", unique = true)
	private String _originalImageURL;

	@Min(0)
	@Column(name = "old_price", columnDefinition = "decimal(6,2) default 0.0")
	private Double _oldPrice;

	@Min(0)
	@Column(name = "price", columnDefinition = "decimal(6,2) default 0.0")
	private Double _price;

	@Size(min = 0, max = 1000)
	@Column(name = "requirements")
	private String _requirements;

	@URL
	@Column(name = "smartybro_url", unique = true, nullable = false, length = 300)
	private String _smartybroURL;

	@Min(0)
	@Builder.Default
	@Column(name = "students_enrolled", columnDefinition = "integer default 0")
	private Integer _studentsEnrolled = 0;

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id")
	private Subcategory _subcategory;

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic _topic;

	@NotBlank(message = "Title must not be blank!")
	@Column(name = "title", unique = true, nullable = false)
	private String _title;

	@Column(name = "udemy_id", unique = true, nullable = false)
	private Long _udemyId;

	@URL
	@Column(name = "udemy_url", unique = true, nullable = false, length = 300)
	private String _udemyURL;

	// ratings
	@Min(0)
	@Builder.Default
	@Column(name = "rating_count_1", columnDefinition = "integer default 0")
	private Integer _ratingCount1 = 0;

	@Builder.Default
	@Column(name = "is_coupon_active", columnDefinition = "boolean default true")
	private Boolean _couponActive = true;

	@Builder.Default
	@Column(name = "is_archived", columnDefinition = "boolean default false")
	private Boolean _archived = false;

	@Min(0)
	@Builder.Default
	@Column(name = "rating_count_2", columnDefinition = "integer default 0")
	private Integer _ratingCount2 = 0;

	@Min(0)
	@Builder.Default
	@Column(name = "rating_count_3", columnDefinition = "integer default 0")
	private Integer _ratingCount3 = 0;

	@Min(0)
	@Builder.Default
	@Column(name = "rating_count_4", columnDefinition = "integer default 0")
	private Integer _ratingCount4 = 0;

	@Min(0)
	@Builder.Default
	@Column(name = "rating_count_5", columnDefinition = "integer default 0")
	private Integer _ratingCount5 = 0;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp _updatedAt;

	public void setSubcategory(Subcategory subcategory) {
		_subcategory = subcategory;
		subcategory.addCourse(this);
	}

	public void setTopic(Topic topic) {
		_topic = topic;
		topic.addCourse(this);
	}

}
