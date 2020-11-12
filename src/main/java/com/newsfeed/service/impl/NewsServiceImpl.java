package com.newsfeed.service.impl;


import com.newsfeed.model.Category;
import com.newsfeed.model.News;
import com.newsfeed.repo.NewsCrudRepo;
import com.newsfeed.repo.NewsRepo;
import com.newsfeed.service.CategoryService;
import com.newsfeed.service.NewsService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private final NewsCrudRepo newsCrudRepo;
    private final CategoryService categoryService;
    private final NewsRepo newsRepo;

    @Autowired
    public NewsServiceImpl(NewsCrudRepo newsCrudRepo, CategoryService categoryService, NewsRepo newsRepo) {
        this.newsCrudRepo = newsCrudRepo;
        this.categoryService = categoryService;
        this.newsRepo = newsRepo;
    }

    @Override
    public Boolean isNewsExist(String name) {
        if (name.equals("")) return null;
        return newsCrudRepo.isExistsByName(name);
    }

    @Override
    public List<News> findByCategory(Category category) {
        if (category.equals("")) return null;
        return newsCrudRepo.findByCategory(category);
    }

    @Override
    public News findByName(String name) {
        if (name.equals("")) return null;
        return newsCrudRepo.findByName(name);
    }

    @Override
    public List<News> findByContent(String content) {
        if (content.equals("")) return null;
        return newsRepo.findByContent(content);
    }

    @Override
    public News save(News news) {
//        if (newsCrudRepo.isExistsByName(news.getName())) return null;
        News finedNews = newsCrudRepo.findByName(news.getName());
        if (finedNews != null && !finedNews.getId().equals(news.getId())) return null;
        Category category = categoryService.findByName(news.getCategory().getName());
        if (category != null) {
            news.setCategory(category);
        }
        return newsCrudRepo.save(news);
    }

    @Override
    public News update(News news) {
        News finedNews = newsCrudRepo.findByName(news.getName());
        if (finedNews != null && !finedNews.getId().equals(news.getId())) return null;
//        if (!finedNews.getId().equals(news.getId())) return null;
        return newsCrudRepo.save(news);
    }

    @Override
    public List<News> getAll() {
        return Lists.newArrayList(newsCrudRepo.findAll());
    }

    @Override
    public void delete(long id) {
        newsCrudRepo.delete(id);
    }
}
