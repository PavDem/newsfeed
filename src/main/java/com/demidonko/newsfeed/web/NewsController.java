package com.demidonko.newsfeed.web;

import com.demidonko.newsfeed.model.Category;
import com.demidonko.newsfeed.model.News;
import com.demidonko.newsfeed.service.CategoryService;
import com.demidonko.newsfeed.service.NewsService;
import com.demidonko.newsfeed.service.impl.CategoryServiceImpl;
import com.demidonko.newsfeed.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class NewsController {
    private final CategoryService categoryService;
    private final NewsService newsService;

    @Autowired
    public NewsController(CategoryServiceImpl categoryService, NewsService newsService) {
        this.categoryService = categoryService;
        this.newsService = newsService;
    }

    @GetMapping("/")
    public List<News> getAll() {
        return newsService.getAll();
    }


    @PostMapping("/")
    public Object save(@RequestBody News news) {
        return newsService.save(news);

    }

    @GetMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        newsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public List<News> findNewsByCategory(@PathVariable Category category) {
        return newsService.findByCategory(category);

    }

    @GetMapping("/{name}")
    public List<News> findNewsByByName(@PathVariable String name) {
        return newsService.findByName(name);
    }

    public List<News> findNewsByContent() {
        return null;
    }
}
