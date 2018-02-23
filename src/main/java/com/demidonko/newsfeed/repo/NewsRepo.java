package com.demidonko.newsfeed.repo;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface NewsRepo extends CrudRepository<News, Long> {
    List<News> findByName(String name);

    List<News> findByCategory(Category category);

}
