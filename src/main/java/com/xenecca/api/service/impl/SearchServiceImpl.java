package com.xenecca.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.es.CourseDocRepository;
import com.xenecca.api.dao.es.LearningResourceDocRepository;
import com.xenecca.api.model.elastic.CourseDoc;
import com.xenecca.api.model.elastic.LearningResourceDoc;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.model.type.MaterialType;
import com.xenecca.api.model.type.ResourceType;
import com.xenecca.api.service.SearchService;
import com.xenecca.api.utils.SortAndCompareUtils;
import com.xenecca.api.utils.model.PageResult;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private CourseDocRepository _courseDocRepository;

    @Autowired
    private LearningResourceDocRepository _resourceDocRepository;

    @Autowired
    private ElasticsearchOperations _template;

    private Criteria createCourseCriteriaBasedOnParams(String searchTerm, Integer categoryId, Integer subcategoryId) {
        Criteria criteria = new Criteria();
        if (searchTerm != null && !searchTerm.isEmpty()) {
            criteria.subCriteria(new Criteria("title").contains(searchTerm).or("headline").contains(searchTerm));
        }

        if (categoryId != null || subcategoryId != null) {
            if (subcategoryId != null) {
                criteria.subCriteria(new Criteria("subcategory").matches(subcategoryId));
            } else {
                criteria.subCriteria(new Criteria("category").matches(categoryId));
            }
        }

        return criteria;
    }

    private Criteria createResourceCriteriaBasedOnParams(String searchTerm, Long categoryId, ResourceType resourceType,
                                                         MaterialType materialType) {
        Criteria criteria = new Criteria();
        if (searchTerm != null && !searchTerm.isEmpty()) {
            criteria.subCriteria(new Criteria("name"));
        }
        if (categoryId != null) {
            criteria.subCriteria(new Criteria("category").matches(categoryId));
        }

        if (resourceType != null) {
            criteria.subCriteria(new Criteria("resource_type").matches(resourceType));
        }

        if (materialType != null) {
            criteria.subCriteria(new Criteria("material_type").matches(materialType));
        }
        return criteria;

    }

    @Override
    public void deleteCourseDocument(Long courseId) {
        getCourseDocRepository().deleteById(courseId);
    }

    @Override
    public void deleteResourceDocument(Long resourceId) {
        getResourceDocRepository().deleteById(resourceId);
    }

    @Override
    public PageResult<CourseDoc> searchCourses(String searchTerm, Integer categoryId, Integer subcategoryId,
                                               Integer pageNo, Integer pageSize) {

        Criteria criteria = createCourseCriteriaBasedOnParams(searchTerm, categoryId, subcategoryId);
        Query query = new CriteriaQuery(criteria);
        Pageable pageable = SortAndCompareUtils.createPageable(pageNo, pageSize, "date", "desc");
        query.setPageable(pageable);
        return search(query, CourseDoc.class);
    }

    @Override
    public PageResult<LearningResourceDoc> searchResources(String searchTerm, Long categoryId,
                                                           ResourceType resourceType, MaterialType materialType, Integer pageNo, Integer pageSize) {
        Criteria criteria = createResourceCriteriaBasedOnParams(searchTerm, categoryId, resourceType, materialType);
        Query query = new CriteriaQuery(criteria);
        Pageable pageable = SortAndCompareUtils.createPageable(pageNo, pageSize, null, null);
        query.setPageable(pageable);
        return search(query, LearningResourceDoc.class);
    }

    private <T> PageResult<T> search(Query searchQuery, Class<T> clazz) {
        List<T> resultList = new ArrayList<T>();
        SearchHits<T> results = getTemplate().search(searchQuery, clazz);
        for (SearchHit<T> resourceHit : results.getSearchHits()) {
            T doc = resourceHit.getContent();
            if (doc != null) {
                resultList.add(doc);
            }

        }
        return new PageResult<T>(resultList, results.getTotalHits(), searchQuery.getPageable().getPageSize());

    }

    @Override
    public void storeResourceDocument(LearningResource resource) {
        LearningResourceDoc doc = LearningResourceDoc.builder().docId(resource.getId())
                .category(resource.getResourceCategory().getId()).name(resource.getName())
                .materialType(resource.getMaterialType().toString()).resource(resource.getResource())
                .resourceType(resource.getResourceType().getName()).build();
        getResourceDocRepository().save(doc);
    }

}
