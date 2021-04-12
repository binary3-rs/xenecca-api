package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CourseCouponRepository;
import com.xenecca.api.dto.request.NewCourseCouponDTO;
import com.xenecca.api.model.CourseCoupon;
import com.xenecca.api.service.CourseCouponService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class CourseCouponServiceImpl implements CourseCouponService {

	@Autowired
	private CourseCouponRepository _courseCouponRepository;

	@Override
	public boolean addCourseCoupon(NewCourseCouponDTO courseCouponData) throws Exception {
		String couponCode = courseCouponData.getDiscountCode();
		String url = courseCouponData.getUrl();
		String[] urlComponents;
		if (couponCode.equals(null)) {
			if (!url.contains("couponCode")) {
				throw new Exception("The URL you provided does not contain information about coupon code.");
			} else {
				urlComponents = url.split("couponCode=");
				if (urlComponents.length > 1) {
					couponCode = urlComponents[1];
				}
			}
		}

		CourseCoupon.builder().courseUrl(url).couponCode(couponCode).build();
		return true;
	}

	@Override
	public Iterable<CourseCoupon> getAllCoupons() {
		return getCourseCouponRepository().findAll();
	}

	@Override
	public CourseCoupon approveCoupon(Long id) throws Exception {
		CourseCoupon coupon = getCourseCouponRepository().findById(id)
				.orElseThrow(() -> new Exception("Course coupon with the given iddoes not exist!"));
		// handle 404
		if (coupon.getApproved()) {
			throw new Exception("Course is already approved!");
		}
		coupon.setApproved(true);
		getCourseCouponRepository().save(coupon);
		return coupon;

	}

}
