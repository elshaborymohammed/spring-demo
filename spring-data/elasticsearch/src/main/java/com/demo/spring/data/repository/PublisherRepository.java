package com.demo.spring.data.repository;

import com.demo.spring.data.domain.Article;
import com.demo.spring.data.domain.Publisher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends ElasticsearchRepository<Publisher, String> {

    @Override
    List<Publisher> findAll();

    List<Article> Name(String name);
}
