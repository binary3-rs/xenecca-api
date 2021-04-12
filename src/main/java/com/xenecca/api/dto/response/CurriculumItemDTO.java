package com.xenecca.api.dto.response;

import com.xenecca.api.model.CurriculumItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurriculumItemDTO {
	private String _title;
	private Integer _index;
	private String _description;
	private Integer _contentLength;

	public CurriculumItemDTO(CurriculumItem item) {
		_title = item.getTitle();
		_index = item.getIndex();
		_description = item.getDescription();
		_contentLength = item.getContentLength();
	}

}
