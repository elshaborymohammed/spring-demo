package com.demo.spring.data.repository;

import com.demo.spring.data.domain.Author;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends ElasticsearchRepository<Author, String> {

    @Override
    List<Author> findAll();

    List<Author> findByName(String name);
}
