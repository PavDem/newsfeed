package com.demidonko.newsfeed.web;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import com.demidonko.newsfeed.service.CategoryService;
import com.demidonko.newsfeed.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/news")
public class NewsController {
    private final CategoryService categoryService;
    private final NewsService newsService;

    @Autowired
    public NewsController(CategoryService categoryService, NewsService newsService) {
        this.categoryService = categoryService;
        this.newsService = newsService; }

    @GetMapping("/")
    public List<News> getAll() {
        return newsService.getAll();
    }

    @PostMapping("/save")
    public News save(@RequestBody News news) {
        return newsService.save(news);

    }

    @PostMapping(value = "/update/{id}")
    public News update(@RequestBody News news, @PathVariable long id) {
        news.setId(id);
        return newsService.update(news);

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        newsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("search/by-category/{category}")
    public List<News> findNewsByCategory(@PathVariable String category) {
        return newsService.findByCategory(new Category(category));

    }

    @GetMapping("search/by-name/{name}")
    public News findNewsByByName(@PathVariable String name) {
        return newsService.findByName(name);
    }

    @GetMapping("/search/by-content/{content}")
    public List<News> findNewsByContent(@PathVariable String content) {
        return newsService.findByContent(content);
    }

}
