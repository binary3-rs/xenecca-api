package com.xenecca.api.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class CourseCoupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@URL
	@Column(name = "course_url", unique = true, nullable = false)
	private String _courseUrl;
	@Column(name = "coupon_code", length = 40)
	private String _couponCode;

	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp _createdAt;
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp _updatedAt;

	@Builder.Default
	@Column(name = "approved", columnDefinition = "boolean default false")
	private Boolean _approved = false;

	@Builder.Default
	@Column(name = "scrapped", columnDefinition = "boolean default false")
	private Boolean _scrapped = false;

}
