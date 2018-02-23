package com.demidonko.newsfeed.service;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import com.demidonko.newsfeed.repo.CategoryRepo;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsService newsService;

    private static final Category category1 = new Category("TEST");
    private static final Category category2 = new Category("TEST2");
    private static final Category category3 = new Category("TEST3");
    private static final Category category4 = new Category("TEST4");
    private static final News news1 = new News("testnews", category1, "TEST CONTENT");
    private static final News news1SameName = new News("testnews", category1, "TEST CONTENT");
    private static final News news2 = new News("testnews2", category2, "TEST CONTENT2");
    private static final News news3 = new News("testnews3", category3, "PHRASE ONE TWO THREE SEARCH TEST");
    private static final News news4 = new News("testnews4", category4, "");

    @Test
    public void serviceSaveTest() throws Exception {
        Category savedCategory1 = categoryService.save(category1);
        Category savedCategory2 = categoryService.save(category2);
        savedCategory1.setNewses(new ArrayList<>());
        savedCategory2.setNewses(new ArrayList<>());
        List<Category> categories = categoryService.getAll();
        assertTrue(categories.contains(savedCategory1));
        assertTrue(categories.contains(savedCategory2));

    }

    @Test
    public void serviceUpdateTest() throws Exception {
        Category newCategory = categoryService.save(category1);
        newCategory.setName("NEW CATEGORY NAME");
        List<Category> categories = categoryService.getAll();
        categories.contains(newCategory);
    }


    @Test
    public void serviceDeleteTest() throws Exception {
        Category newCategory = categoryService.save(category1);
        categoryService.delete(newCategory.getId());
        List<Category> categories = categoryService.getAll();
        assertTrue(categories.isEmpty());

    }

    @Test
    public void serviceFindByNameTest() throws Exception {
        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);

        newsService.save(news1);
        newsService.save(news2);
        newsService.save(news1SameName);
        newsService.save(news3);

        List<Category> categories1 = categoryService.findByName(category1.getName());

        List<Category> allCategories = categoryService.getAll();

        News test1 = new News("testnews", category1, "TEST CONTENT");
        News test2 = new News("testnews", category1, "TEST CONTENT");

        Category categoryTest1 = new Category("Test");
        Category categoryTest2 = new Category(" Test");

        test1.setCategory(categoryTest1);
        test2.setCategory(categoryTest1);


        categoryTest1.equals(categoryTest2);
        test1.equals(test2);


        categories1.get(0).equals(allCategories.get(0));

    }
}
