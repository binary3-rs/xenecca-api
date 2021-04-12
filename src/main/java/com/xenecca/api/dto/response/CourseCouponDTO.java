package com.xenecca.api.dto.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
@Accessors(prefix = "_")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseCouponDTO {
	private Long _id;
	private String _url;
	private String _discountCode;
	private Timestamp _createdAt;
	private Timestamp _updatedAt;
	private Boolean _approved;
	private Boolean _scrapped;
}
