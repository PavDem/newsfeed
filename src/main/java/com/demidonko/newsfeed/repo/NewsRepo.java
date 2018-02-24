package com.demidonko.newsfeed.repo;

import com.demidonko.newsfeed.model.News;

import java.util.List;

public interface NewsRepo {
    List<News> findByContent(String content);
}
