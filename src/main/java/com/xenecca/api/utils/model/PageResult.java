package com.xenecca.api.utils.model;

import java.util.List;

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
public class PageResult<T> {

	private List<T> _results;
	private long _numOfResults;
	private int _pageSize;
}
