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
		case "date_added":
			return "_updatedAt";
		case "popularity":
			return "_redeemedCouponCount";
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

	public static Pageable createPageable(Integer pageNo, String sortBy, String order, Integer pageSize) {
		if (pageSize != null) {
			return _createPageable(pageNo, sortBy, order, pageSize);
		}
		return createPageable(pageNo, sortBy, order);
	}

	public static Pageable createPageable(Integer pageNo, String sortBy, String order) {

		if (sortBy == null && order == null) {
			return PageRequest.of(pageNo, Constants.RESOURCES_PAGE_SIZE);
		}
		return _createPageable(pageNo, sortBy, order, Constants.PAGE_SIZE);
	}

	private static Pageable _createPageable(Integer pageNo, String sortBy, String order, int size) {
		String sortByField = SortAndCompareUtils.sortField(sortBy);
		Sort sort = SortAndCompareUtils.sortOrder(sortByField, order);
		return PageRequest.of(pageNo, size, sort);
	}

}
