package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.response.CourseCouponDTO;
import com.xenecca.api.model.CourseCoupon;

@Mapper(componentModel = "spring")
public interface CourseCouponMapper {
	CourseCouponDTO mapToDTO(CourseCoupon coupon);

	List<CourseCouponDTO> mapToDTOList(Iterable<CourseCoupon> coupons);
}
