package com.xenecca.api.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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

@Accessors(prefix = "_")
@Getter
@Setter
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
		}
	}

	public void populateCategories() {
		String[] categories = { "Business", "Design", "Development", "Finance & Accounting", "IT & Software",
				"Marketing", "Music", "Office", "Productivity", "Personal Development", "Photography & Video",
				"Teaching & Academics" };
		for (String category : categories) {
			try {
				getCategoryRepository().save(new Category(category));
			} catch (Exception e) {
				continue;
			}
		}
	}

	public void populateResourceCategories(int x) throws ParseException, IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new ClassPathResource("tech-categories.txt").getInputStream()));
		String st;
		while ((st = reader.readLine()) != null) {
			String[] components = st.split("-!!-");
			try {
				String name = components[1];
				LearningResourceDomain domain = LearningResourceDomain.valueOf(components[0]);
				String tags = (components.length > 2) ? components[2] : null;
				LearningResourceCategory category = getResourceCategoryRepository().findBy_name(components[1]);
				if (category == null) {
					category = new LearningResourceCategory(name, domain, tags, null);
					getResourceCategoryRepository().save(category);
				} else {
					category.setDomain(domain);
					category.setTags(tags);
					getResourceCategoryRepository().save(category);
				}
			} catch (Exception e) {
				continue;

			}
		}

	}
}
