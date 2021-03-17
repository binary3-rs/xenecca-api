package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.CourseCoupon;

@Repository
public interface CourseCouponRepository extends CrudRepository<CourseCoupon, Long> {
	public Iterable<CourseCoupon> findBy_approved(Boolean approved);

	public Iterable<CourseCoupon> findBy_scrapped(Boolean scrapped);
}
