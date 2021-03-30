package com.xenecca.api.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SortAndCompareUtils {
	private static final int PAGE_SIZE = 18;

	private static String sortField(String sortBy) {
		switch (sortBy) {
		case "date_added":
			return "_updatedAt";
		case "rating":
			return "_avgRating";
		case "num_of_students":
			return "_numOfStudents";
		default:
			return "_updatedAt";
		}
	}

	private static Sort sortOrder(String sortByField, String order) {
		return order.equals("asc") ? Sort.by(sortByField).ascending() : Sort.by(sortByField).descending();

	}

	public static Pageable createPageable(Integer pageNo, String sortBy, String order) {
		String sortByField = SortAndCompareUtils.sortField(sortBy);
		Sort sort = SortAndCompareUtils.sortOrder(sortByField, order);
		return PageRequest.of(pageNo, PAGE_SIZE, sort);
	}

}
