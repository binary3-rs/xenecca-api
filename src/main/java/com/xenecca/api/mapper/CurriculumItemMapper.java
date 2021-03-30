package com.xenecca.api.mapper;

import java.util.ArrayList;
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

			if (item.getItemType().contentEquals("section")) {
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
		return new ArrayList<CurriculumSectionDTO>(curriculumSections.values());
	}

//    private int compareItems(CurriculumItem item1, CurriculumItem item2) {
//    	if(item1.getIndex() < item1.)
//        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//        return lhs.customInt > rhs.customInt ? -1 : (lhs.customInt < rhs.customInt) ? 1 : 0;
//    } 

}
