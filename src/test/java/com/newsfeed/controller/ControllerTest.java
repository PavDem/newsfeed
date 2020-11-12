package com.newsfeed.controller;

import com.newsfeed.model.Category;
import com.newsfeed.model.News;
import com.newsfeed.service.CategoryService;
import com.newsfeed.service.NewsService;
import com.newsfeed.web.NewsController;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(NewsController.class)
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
    private static final News news4 = new News("", category4, "");

    @Test
    public void saveNewsTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        when(newsService.save(news1)).thenReturn(news1);
        String result = mapper.writeValueAsString(news1);
        mockMvc.perform(post("/save/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    public void saveNewsWhenNameIsEmpty() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        when(newsService.save(news4)).thenReturn(news4);
        String result = mapper.writeValueAsString(news4);
        mockMvc.perform(post("/save/")
                .content(result)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void updateNewsTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(news1);
        when(newsService.update(news1)).thenReturn(news1);
        mockMvc.perform(post("/update/10" )
                .content(result)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(result))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteTestWhenNewsExistTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        when(newsService.save(news1)).thenReturn(news1);
        doNothing().when(newsService).delete(10);
        String result = mapper.writeValueAsString(news1);
        mockMvc.perform(get("/delete/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andExpect(status().isOk());
    }




    /** TODO
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
