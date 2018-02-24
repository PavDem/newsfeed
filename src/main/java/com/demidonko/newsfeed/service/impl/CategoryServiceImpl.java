package com.demidonko.newsfeed.service.impl;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.repo.CategoryCrudRepo;
import com.demidonko.newsfeed.service.CategoryService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryCrudRepo categoryCrudRepo;

    @Autowired
    public CategoryServiceImpl(CategoryCrudRepo categoryCrudRepo) {
        this.categoryCrudRepo = categoryCrudRepo;
    }

    @Override
    public Boolean isCategoryExist(String name) {
        return categoryCrudRepo.isExistsByName(name);
    }

    @Override
    public Category findByName(String name) {
        return categoryCrudRepo.findByName(name);
    }

    @Override
    public Category save(Category category) {
        Category cat = categoryCrudRepo.findByName(category.getName());
            if (cat != null) return null;
            return categoryCrudRepo.save(category);
    }

    @Override
    public Category delete(long id) {
        categoryCrudRepo.delete(id);
        return null;
    }

    @Override
    public List<Category> getAll() {
        return Lists.newArrayList(categoryCrudRepo.findAll());
    }
}
