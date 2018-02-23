package com.demidonko.newsfeed.controller;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import com.demidonko.newsfeed.service.CategoryService;
import com.demidonko.newsfeed.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ControllerTest.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    WebApplicationContext wac;

    @MockBean
    private NewsService newsService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    public ControllerTest() {
    }

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
    public void saveNewsTest() throws Exception {
        News news = news1;
        ObjectMapper mapper = new ObjectMapper();
        given(newsService.save(news)).willReturn(news);
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(news)))
                .andExpect(status().isOk())
                .andExpect(content().string(news.getContent()));
    }


 /*
    public void saveNewsWhenNewsIsNullTest();

    public void updateNewsTest();

    public void deleteTestWhenNewsExistTest();

    public void deleteTestWhenNewsNotExistTest();

    public void findByCategoryWhenCategoryIsNullTest();

    public void findByCategoryWhenCategoryExistTest();

    public void findByCategoryWhenCategoryNotExistTest();

    public void findNewsByNameWhenNameIsNullTest();

    public void findNewsByNameWhenNewsExistTest();

    public void findNewsByNameWhenNewsExistAndMoreThanOneTest();

    public void findNewsByNameWhenNewsNotExistTest();
    */

}
