package com.xenecca.api.service;

import com.xenecca.api.dto.request.NewCourseCouponDTO;
import com.xenecca.api.model.CourseCoupon;

public interface CourseCouponService {

	public boolean addCourseCoupon(NewCourseCouponDTO courseCouponData) throws Exception;

	public Iterable<CourseCoupon> getAllCoupons();

	public CourseCoupon approveCoupon(Long id) throws Exception;
	// public CourseCouponDTO updateCoupon(Long id); - not necessary as of now

}
