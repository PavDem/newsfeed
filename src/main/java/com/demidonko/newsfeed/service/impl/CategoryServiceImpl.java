package com.demidonko.newsfeed.service.impl;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import com.demidonko.newsfeed.repo.CategoryRepo;
import com.demidonko.newsfeed.service.CategoryService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepo.findByName(name);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category delete(long id) {
        categoryRepo.delete(id);
        return null;
    }

    @Override
    public List<Category> getAll() {
        return Lists.newArrayList(categoryRepo.findAll());
    }
}
