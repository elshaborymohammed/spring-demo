package com.demo.spring.data.jpa.repository;

import com.demo.spring.data.jpa.JpaApplication;
import com.demo.spring.data.jpa.domain.Publisher;
import org.hibernate.engine.spi.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpaApplication.class)
class PublisherRepositoryTest {

    @Autowired
    PublisherRepository repository;
    @Autowired
    EntityManager manager;
    @Autowired
    PersistenceContext context;

    @Test
    void findAll() {
        System.out.println(repository.findAll(Pageable.unpaged()));
    }

    @Test
    void criteriaQuery() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Publisher> query = builder.createQuery(Publisher.class);
        Root<Publisher> publisherRoot = query.from(Publisher.class);

        query.where(
                builder.like(publisherRoot.get("name"), "Blog")
        );

        

        System.out.println(repository.findAll(Pageable.unpaged()));
    }
}