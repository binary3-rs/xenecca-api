package com.xenecca.api.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xenecca.api.dto.response.CurriculumItemDTO;
import com.xenecca.api.dto.response.CurriculumSectionDTO;
import com.xenecca.api.model.CurriculumItem;

public class CurriculumItemMapper {

	public static List<CurriculumSectionDTO> mapToCurriculumDTO(Set<CurriculumItem> items) {
		Map<Integer, CurriculumSectionDTO> curriculumSections = new HashMap<Integer, CurriculumSectionDTO>();
		CurriculumSectionDTO currentSection = null;
		Map<Integer, List<CurriculumItemDTO>> lecturesToAdd = new HashMap<Integer, List<CurriculumItemDTO>>();
		int sectionIndex;
		for (CurriculumItem item : items) {

			if (item.getItemType().equals("section")) {
				currentSection = new CurriculumSectionDTO(item);
				sectionIndex = item.getIndex();
				if (lecturesToAdd.containsKey(sectionIndex) && lecturesToAdd.get(sectionIndex).size() > 0) {
					currentSection.addLectures(lecturesToAdd.get(sectionIndex));
					lecturesToAdd.get(sectionIndex).clear();
				}
				curriculumSections.put(currentSection.getIndex(), currentSection);
			} else {
				sectionIndex = item.getSectionIndex();
				CurriculumItemDTO lecture = new CurriculumItemDTO(item);
				if (currentSection != null && currentSection.getIndex() == sectionIndex) {
					currentSection.addLecture(lecture);
				} else if (curriculumSections.containsKey(sectionIndex)) {
					curriculumSections.get(sectionIndex).addLecture(lecture);
				} else {
					if (!lecturesToAdd.containsKey(sectionIndex)) {
						lecturesToAdd.put(sectionIndex, new ArrayList<CurriculumItemDTO>());
					}
					lecturesToAdd.get(sectionIndex).add(lecture);
				}
			}
		}
		// in case, prev algorithm doesn't work
		/*
		 * for(Map.Entry<Integer, List<CurriculumItemDTO>>
		 * entry:lecturesToAdd.entrySet()) {
		 * curriculumSections.get(entry.getKey()).addLectures(entry.getValue()); }
		 */

		for (int key : curriculumSections.keySet()) {
			Collections.sort(curriculumSections.get(key).getLectures(), new CurriculumItemComparator());

		}
		List<CurriculumSectionDTO> curriculum = new ArrayList<CurriculumSectionDTO>(curriculumSections.values());
		Collections.sort(curriculum, new CurriculumSectionComparator());
		return curriculum;
	}
}

class CurriculumItemComparator implements Comparator<CurriculumItemDTO> {

	@Override
	public int compare(CurriculumItemDTO item1, CurriculumItemDTO item2) {
		return Integer.compare(item1.getIndex(), item2.getIndex());
	}

}

class CurriculumSectionComparator implements Comparator<CurriculumSectionDTO> {

	@Override
	public int compare(CurriculumSectionDTO item1, CurriculumSectionDTO item2) {
		return Integer.compare(item1.getIndex(), item2.getIndex());
	}

}
