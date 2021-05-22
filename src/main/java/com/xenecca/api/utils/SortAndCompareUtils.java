package com.xenecca.api.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
public class SortAndCompareUtils {

	private static String sortField(String sortBy) {
		switch (sortBy) {
		case "date":
			return "_updatedAt";
		case "popularity":
			return "_redeemedCouponCount";
		case "id":
			return "_id";
		case "doc_id":
			return "_docId";
		default:
			return "_updatedAt";
		}
	}

	private static Sort sortOrder(String sortByField, String order) {
		return order.equals("asc") ? Sort.by(sortByField).ascending() : Sort.by(sortByField).descending();
	}

	public static Pageable createPageable(Integer pageNo, Integer pageSize, String sortBy, String order) {
		if (sortBy == null && order == null) {
			return PageRequest.of(pageNo, pageSize);
		}

		return _createPageable(pageNo, pageSize, sortBy, order);
	}

	private static Pageable _createPageable(Integer pageNo, Integer pageSize, String sortBy, String order) {
		String sortByField = SortAndCompareUtils.sortField(sortBy);
		Sort sort = SortAndCompareUtils.sortOrder(sortByField, order);
		return PageRequest.of(pageNo, pageSize, sort);
	}

}
