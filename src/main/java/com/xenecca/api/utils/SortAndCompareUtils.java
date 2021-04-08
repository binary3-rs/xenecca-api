package com.xenecca.api.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SortAndCompareUtils {

	private static String sortField(String sortBy) {
		switch (sortBy) {
		case "date_added":
			return "_updatedAt";
		case "rating":
			return "_avgRating";
		case "num_of_students":
			return "_numOfStudents";
		case "name":
			return "_name";
		case "title":
			return "_title";
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

	public static Pageable createPageable(Integer pageNo, String sortBy, String order) {
		if(sortBy == null && order == null) {
			return PageRequest.of(pageNo, Constants.RESOURCES_PAGE_SIZE);
		}
		String sortByField = SortAndCompareUtils.sortField(sortBy);
		Sort sort = SortAndCompareUtils.sortOrder(sortByField, order);
		return PageRequest.of(pageNo, Constants.PAGE_SIZE, sort);
	}

}
