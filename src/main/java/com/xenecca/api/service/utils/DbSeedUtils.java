package com.xenecca.api.service.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.xenecca.api.dao.CategoryRepository;
import com.xenecca.api.dao.LearningResourceCategoryRepository;
import com.xenecca.api.dao.UserRepository;
import com.xenecca.api.model.Category;
import com.xenecca.api.model.User;
import com.xenecca.api.model.learnresource.LearningResourceCategory;
import com.xenecca.api.model.type.LearningResourceDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@Component
public class DbSeedUtils {

	@Autowired
	private UserRepository _userRepository;

	@Autowired
	private CategoryRepository _categoryRepository;

	@Autowired
	private LearningResourceCategoryRepository _resourceCategoryRepository;

	@Autowired
	private PasswordEncoder _passwordEncoder;

	public void addUser() {
		User user = User.builder().firstName("xenecca").lastName("admin").username("admin").email("xenecca@gmail.com")
				.password(getPasswordEncoder().encode("xeneccaMaNiNe")).build();
		try {
			getUserRepository().save(user);
		} catch (Exception e) {
			// log
		}
	}

	public void populateCategories() {
		String[] categories = { "Business", "Design", "Development", "Finance & Accounting", "IT & Software",
				"Marketing", "Music", "Office", "Productivity", "Personal Development", "Photography & Video",
				"Teaching & Academics" };
		for (String category : categories) {
			getCategoryRepository().save(new Category(category));
		}
	}


	public void populateResourceCategories() {
		String[] webdevCategories = { "HTML", "CSS", "JavaScript", "Angular", "AngularJS", "ReactJS", "VueJS",
				"ASP.NET", "Spring", "GraphQL", "jQuery", "Laravel", ".NET Core", "Node.js", "PHP", "PWA", "Django",
				"Flask", "Redux", "Ruby on Rails", "Typescript", "UX design", "Web security" };
		String[] mobiledevCategories = { "Android", "Kotlin", "Flutter", "Swift", "React Native", "Xamarin" };
		String[] devopsCategories = { "Git", "Docker", "Kubernetes", "Ansible", "Puppeteer", "AWS", "Azure", "GCP" };
		String[] dsaCategories = { "Complexity analysis", "LinkedList", "Stack", "Queue", "Arrays & Matrices",
				"Binary Tree", "Binary Search Tree", "Heap", "Graph", "Greedy", "Dynamic programming", "Recursion",
				"Strings", "Hashing" };
		String[] dataScienceCategories = { "Linear regression", "Logistic regression", "SVM", "kNN", "Neural networks",
				"CNN", "PCA" };
		String[] databaseCategories = { "SQL", "T-SQL", "MySQL", "PostgreSQL", "MS SQL Server", "MongoDB", "neo4j" };
		String[] sysdesignCategories = { "REST API Design", "Caching", "Microservices", "CAP Theorem" };
		Map<LearningResourceDomain, String[]> categoryMap = Map.of(LearningResourceDomain.WEBDEV, webdevCategories,
				LearningResourceDomain.MOBILE, mobiledevCategories, LearningResourceDomain.DEVOPS, devopsCategories,
				LearningResourceDomain.DSA, dsaCategories, LearningResourceDomain.DATASCIENCE, dataScienceCategories,
				LearningResourceDomain.DATABASES, databaseCategories, LearningResourceDomain.SYSDESIGN,
				sysdesignCategories);
		for (LearningResourceDomain domain : categoryMap.keySet()) {
			for (String category : categoryMap.get(domain)) {
				try {
					getResourceCategoryRepository().save(new LearningResourceCategory(category, domain));
				} catch (Exception e) {
					continue;
				}
			}
		}

	}

}
