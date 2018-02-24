package com.demidonko.newsfeed.repo;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryCrudRepo extends CrudRepository<Category, Long> {
    Category findByName(String name);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Category c WHERE c.name = :name")
    boolean isExistsByName(@Param("name") String name);

}
