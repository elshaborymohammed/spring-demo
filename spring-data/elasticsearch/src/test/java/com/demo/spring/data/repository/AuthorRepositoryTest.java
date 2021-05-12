package com.demo.spring.data.repository;

import com.demo.spring.data.domain.Author;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Test
    @Disabled
    void setup() {
        elasticsearchOperations.indexOps(Author.class).create();
    }

    @Test
    void findAll() {
        repository.findAll().forEach(System.err::println);
    }

    @Test
    void findByName() {
        repository.findByName("BOB").forEach(System.err::println);
    }
}