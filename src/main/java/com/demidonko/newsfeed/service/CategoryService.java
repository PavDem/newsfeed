package com.demidonko.newsfeed.service;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;

import java.util.List;

public interface CategoryService {

    Boolean isCategoryExist(String name);

    Category save(Category category);

    Category delete(long id);

    Category findByName(String name);

    List<Category> getAll();

}
