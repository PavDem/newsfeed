package com.demidonko.newsfeed.repo.impl;

import com.demidonko.newsfeed.model.News;
import com.demidonko.newsfeed.repo.NewsCrudRepo;
import com.demidonko.newsfeed.repo.NewsRepo;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class NewsRepoImpl implements NewsRepo {

    private EntityManager entityManager;



    @Autowired
    public NewsRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<News> findByContent(String content) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(News.class)
                .get();
        Query query = queryBuilder
                .phrase()
                .withSlop(1)
                .onField("content")
                .sentence(content)
                .createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, News.class);
         List<News> results = fullTextQuery.getResultList();
         return results;
    }
}
