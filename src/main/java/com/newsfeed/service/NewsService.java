package com.newsfeed.service;

import com.newsfeed.model.Category;
import com.newsfeed.model.News;

import java.util.List;

public interface NewsService {

    Boolean isNewsExist(String name);

    List<News> findByCategory(Category category);

    News findByName(String name);

    List<News> findByContent(String content);

    News save(News news);

    News update(News news);

    List<News> getAll();

    void delete(long id);
}
