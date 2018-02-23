package com.demidonko.newsfeed.service;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;

import java.util.List;

public interface NewsService {
    List<News> findByCategory(Category category);

    List<News> findByName(String name);

    List<News> findByContent(String content);

    News save(News news);

    List<News> getAll();

    void delete(long id);
}
