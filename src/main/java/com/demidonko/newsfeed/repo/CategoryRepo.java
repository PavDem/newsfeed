package com.demidonko.newsfeed.repo;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoryRepo extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);
}
