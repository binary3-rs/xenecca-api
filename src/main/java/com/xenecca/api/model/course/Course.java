package com.xenecca.api.model.course;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3783958850800043842L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category _category;

	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp _createdAt;

	@Size(min = 0, max = 24000)
	@Column(name = "description")
	private String _description;

	@Column(name = "headline", length = 255)
	private String _headline;

	@URL
	@Column(name = "host_url", unique = true, nullable = false, length = 300)
	private String _hostUrl;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id")
	private Language _language;

	@Size(min = 0, max = 1200)
	@Column(name = "objectives")
	private String _objectives;

	@URL
	@Column(name = "original_poster_url", unique = true)
	private String _originalPosterUrl;

	@Column(name = "poster_path")
	private String _posterPath;

	@Builder.Default
	@Column(name = "published_on_discord")
	private Boolean _publishedOnDiscord = false;

	@Builder.Default
	@Column(name = "redeemed_coupon_count", columnDefinition = "bigint default 0")
	private Long _redeemedCouponCount = 0L;

	@Size(min = 0, max = 1200)
	@Column(name = "requirements")
	private String _requirements;

	@Column(name = "slug", unique = true, nullable = false, length = 350)
	private String _slug;

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id") // , nullable = false)
	private Subcategory _subcategory;

	@NotBlank(message = "Title must not be blank!")
	@Column(name = "title", unique = true, nullable = false)
	private String _title;

	@URL
	@Column(name = "udemy_url", unique = true, nullable = false, length = 300)
	private String _udemyUrl;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp _updatedAt;

	public void setSubcategory(Subcategory subcategory) {
		_subcategory = subcategory;
		subcategory.addCourse(this);
	}

}
