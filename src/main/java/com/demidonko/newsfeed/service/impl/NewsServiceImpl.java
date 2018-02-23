package com.demidonko.newsfeed.service.impl;


import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import com.demidonko.newsfeed.repo.CategoryRepo;
import com.demidonko.newsfeed.repo.NewsRepo;
import com.demidonko.newsfeed.repo.impl.NewsRepoImpl;
import com.demidonko.newsfeed.service.CategoryService;
import com.demidonko.newsfeed.service.NewsService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;
    private final CategoryService categoryService;

    @Autowired
    public NewsServiceImpl(NewsRepo newsRepo, CategoryService categoryService) {
        this.newsRepo = newsRepo;
        this.categoryService = categoryService;
    }

    @Override
    public List<News> findByCategory(Category category) {
        if (category == null) return null;
        return newsRepo.findByCategory(category);
    }

    @Override
    public List<News> findByName(String name) {
        if (name == null) return null;
        return newsRepo.findByName(name);
    }

    @Override
    public List<News> findByContent(String content) {
        if (content == null) return null;
        return null;
    }


    @Override
    public News save(News news) {
        if (news == null) return null;
        List<Category> categories = categoryService.findByName(news.getCategory().getName());
        if (categories.isEmpty()) {
            categoryService.save(news.getCategory());
        }
        return newsRepo.save(news);
    }

    @Override
    public List<News> getAll() {
        return Lists.newArrayList(newsRepo.findAll());
    }

    @Override
    public void delete(long id) {
        newsRepo.delete(id);
    }
}
