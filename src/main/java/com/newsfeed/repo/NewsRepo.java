package com.newsfeed.repo;

import com.newsfeed.model.News;

import java.util.List;

public interface NewsRepo {
    List<News> findByContent(String content);
}
