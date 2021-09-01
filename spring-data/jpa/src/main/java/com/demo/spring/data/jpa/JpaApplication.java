package com.demo.spring.data.jpa;

import com.demo.spring.data.jpa.domain.Article;
import com.demo.spring.data.jpa.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

@SpringBootApplication
public class JpaApplication {

    @Autowired
    ArticleRepository articleRepository;

    EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @PostConstruct
    void post() {
        List<Article> sqlString = (List<Article>) entityManager.createNativeQuery("sqlString", Article.class).getResultList();
        articleRepository.deleteById(4001L);
    }
}
