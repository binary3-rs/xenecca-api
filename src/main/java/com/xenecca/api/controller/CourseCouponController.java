package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewCourseCouponDTO;
import com.xenecca.api.dto.response.CourseCouponDTO;
import com.xenecca.api.mapper.CourseCouponMapper;
import com.xenecca.api.model.CourseCoupon;
import com.xenecca.api.service.CourseCouponService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@RestController
@RequestMapping("/api/v1/coupons/")
public class CourseCouponController {
	@Autowired
	private CourseCouponService _couponService;

	@Autowired
	private CourseCouponMapper _couponMapper;

	@PostMapping
	public boolean addCoupon(@RequestBody NewCourseCouponDTO data) throws Exception {
		return getCouponService().addCourseCoupon(data);

	}

	@GetMapping
	public List<CourseCouponDTO> getCoupons() {
		Iterable<CourseCoupon> coupons = getCouponService().getAllCoupons();
		return getCouponMapper().mapToDTOList(coupons);

	}

}
