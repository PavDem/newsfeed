package com.demidonko.newsfeed.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

@Entity
@Table(name = "news")
public class News {
    private Long id;
    private String name;
    private Date publicationDate;
    private Category category;
    private String content;

    private News() {
    }

    public News(String name, Category category, String content) {
        this.name = name;
        this.category = category;
        this.content = content;

        this.publicationDate = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "publicationDate")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;

        News news = (News) o;

        if (id != null ? !id.equals(news.id) : news.id != null) return false;
        if (name != null ? !name.equals(news.name) : news.name != null) return false;
        if (publicationDate != null ? !publicationDate.equals(news.publicationDate) : news.publicationDate != null)
            return false;
        if (category != null ? !category.equals(news.category) : news.category != null)
            return false;
        return content != null ? content.equals(news.content) : news.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

//    @Override
//    public String toString() {
//        return "News{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", publicationDate=" + publicationDate +
//                ", category=" + category +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
