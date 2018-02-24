package com.demidonko.newsfeed.service;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {

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


    public Date dateParser(Date date) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        String strDate = format.format(date);
        return format.parse(strDate);
    }

    @Test
    public void serviceIsExistTestWhenItsExist() throws Exception {
//        categoryService.save(category1);
        newsService.save(news1);
        Boolean result = newsService.isNewsExist(news1.getName());

        assertTrue(result);
    }

    @Test
    public void serviceIsExistTestWhenItsNotExist() throws Exception {
        Boolean result = newsService.isNewsExist(news1.getName());
        assertTrue(!result);
    }

    @Test
    public void serviceSaveTest() throws Exception {
        categoryService.save(category1);
        newsService.save(news1);
        List<News> list = newsService.getAll();
        News result = list.get(0);

        dateParser(news1.getPublicationDate());
        news1.setPublicationDate(dateParser(news1.getPublicationDate()));
        result.setPublicationDate(dateParser(result.getPublicationDate()));

        assertEquals(result, news1);
    }

    @Test
    public void serviceSaveButCategoryNotExistTest() throws Exception {
        newsService.save(news1);
        List<News> list = newsService.getAll();
        assertTrue(list.contains(news1));
    }

    @Test
    public void serviceSaveCategoryExistTest() throws Exception {
        categoryService.save(category1);
        newsService.save(news1);
        List<News> list = newsService.getAll();
        assertTrue(list.contains(news1));
    }

    @Test
    public void serviceUpdateTest() throws Exception {
        categoryService.save(category1);
        News newNews = newsService.save(news1);
        newNews.setName("TEST NEW CONTENT");
        newsService.save(newNews);
        List<News> result = newsService.getAll();
        assertTrue(result.contains(newNews));
    }

    @Test
    public void serviceDeleteTest() throws Exception {
        categoryService.save(category1);
        newsService.save(news1);
        newsService.delete(news1.getId());
        List<News> result = newsService.getAll();
        assertTrue(result.isEmpty());

    }

    @Test
    public void serviceFindByNameTest() throws Exception {
        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);
        newsService.save(news1);
        newsService.save(news2);
        News savedNews = newsService.save(news3);
        News result = newsService.findByName(savedNews.getName());
        assertEquals(savedNews, result);

    }

    @Test
    public void serviceFindByNameSameNamesCaseTest() throws Exception {
        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);
        newsService.save(news1);
        newsService.save(news1SameName);
        newsService.save(news2);
        newsService.save(news3);

        List<News> newses = newsService.getAll();

        news1.setPublicationDate(dateParser(news1.getPublicationDate()));
        news1SameName.setPublicationDate(dateParser(news1SameName.getPublicationDate()));
        newses.get(0).setPublicationDate(dateParser(newses.get(0).getPublicationDate()));
        newses.get(1).setPublicationDate(dateParser(newses.get(1).getPublicationDate()));

        assertEquals(newses.get(0), news1);
        assertEquals(newses.get(1), news1SameName);


    }

    @Test
    public void serviceFindByCategory() throws Exception {
        categoryService.save(category1);
        categoryService.save(category2);
//        categoryService.save(category3);

        newsService.save(news1);
        newsService.save(news1SameName);
        newsService.save(news2);
        newsService.save(news3);

        List<News> newsesCategory1 = newsService.findByCategory(category1);

        Assert.assertEquals(news1.getId(), newsesCategory1.get(0).getId());
        Assert.assertEquals(news1SameName.getId(), newsesCategory1.get(1).getId());


    }


    @Test
    public void findByContentTest() throws Exception {
        newsService.save(news1);
    }
}
