package com.xenecca.api.dto.response;

import java.util.ArrayList;
import java.util.List;

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
public class CurriculumSectionDTO {
	private String _title;
	private Integer _index;
	private Integer _contentLength;
	private String _description;
	
	@Builder.Default
	private List<CurriculumItemDTO> _lectures = new ArrayList<CurriculumItemDTO>();

	public CurriculumSectionDTO(CurriculumItem item) {
		_title = item.getTitle();
		_index = item.getIndex();
		_description = item.getDescription();
		_contentLength = item.getContentLength();
	}

	public void addLecture(CurriculumItemDTO lecture) {
		_lectures.add(lecture);
	}

	public void addLectures(List<CurriculumItemDTO> lectures) {
		_lectures.addAll(lectures);
	}

}
