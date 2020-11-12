package com.newsfeed.service;

import com.newsfeed.model.Category;

import java.util.List;

public interface CategoryService {

    Boolean isCategoryExist(String name);

    Category save(Category category);

    Category delete(long id);

    Category findByName(String name);

    List<Category> getAll();

}
