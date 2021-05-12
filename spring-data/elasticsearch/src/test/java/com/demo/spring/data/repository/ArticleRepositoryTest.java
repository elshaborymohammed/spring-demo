package com.demo.spring.data.repository;

import com.demo.spring.data.domain.Article;
import com.demo.spring.data.domain.Author;
import com.demo.spring.data.domain.Publisher;
import com.demo.spring.data.domain.Reference;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository repository;

    @Test
    @Disabled
    void init() {
        Author spring = Author.builder().name("spring").build();
        Author bob = Author.builder().name("Bob").build();

        List<Author> authors = List.of(spring, bob);
        List.of(
                Article.builder().content("Elastic Search")
                        .tags(Set.of("spring", "spring_boot", "spring_data", "elastic"))
                        .authors(List.of(spring))
                        .publishedBy(Publisher.builder().name("baeldung").build())
                        .references(List.of(Reference.builder().name("Java").build()))
                        .build(),
                Article.builder().content("Eureka")
                        .tags(Set.of("spring_boot", "spring_cloud", "netflix"))
                        .authors(List.of(bob))
                        .publishedBy(Publisher.builder().id("springID").name("baeldung.com").build())
                        .references(List.of(Reference.builder().name("Spring Framework").build()))
                        .build(),
                Article.builder().content("Kafka")
                        .tags(Set.of("spring_boot", "spring_cloud", "stream"))
                        .authors(authors)
                        .publishedBy(Publisher.builder().name("reflectoring.io").build())
                        .references(List.of(Reference.builder().name("Spring Framework").build()))
                        .build()
        ).forEach(repository::save);
    }

    @Test
    void findAll() {
        repository.findAll().forEach(System.err::println);
    }

    @Test
    @DisplayName("findByContent")
    void findByContent() {
        repository.findByContent("af").forEach(System.err::println);
        System.err.println("----------------------------------------");
        repository.findByContentContaining("af").forEach(System.err::println);
        System.err.println("----------------------------------------");
        repository.findByContentLike("Kafka").forEach(System.err::println);
    }

    @Test
    @DisplayName("findByTag")
    void findByTags() {
        repository.findByTags("spring").forEach(System.err::println);
        System.err.println("----------------------------------------");
        repository.findByTagsContaining("spring").forEach(System.err::println);
    }

    @Test
    @DisplayName("findByPublisher")
    void findByPublishedByName() {
        repository.findByPublishedByName("baeldung").forEach(System.err::println);
        System.err.println("----------------------------------------");
        repository.findByPublishedByNameContaining("baeldung").forEach(System.err::println);
    }

    @Test
    @DisplayName("findByReference")
    void findByReferencesName() {
        repository.findByReferencesName("Java").forEach(System.err::println);
        System.err.println("----------------------------------------");
        repository.findByReferencesNameContaining("ramework").forEach(System.err::println);
        System.err.println("----------------------------------------");
        repository.findByReferencesContaining("ramework").forEach(System.err::println);
    }

    @Test
    @DisplayName("findByAuthor")
    void findByAuthorsName() {
        repository.findByAuthorsName("B").forEach(System.err::println);
        System.err.println("----------------------------------------");
        repository.findByAuthorsNameContaining("bob").forEach(System.err::println);
        System.err.println("----------------------------------------");
        repository.findByAuthorsNameUsingCustomQuery("spring").forEach(System.err::println);
    }

    @Test
    void findByAuthorsNameAndFilteredTagQuery() {
        repository.findByAuthorsNameAndFilteredTagQuery("Java", "spring").forEach(System.err::println);
    }

    @Test
    void nativeQuery() {
        repository.NativeQuery("kafka").forEach(System.err::println);
    }

    @Test
    void nativeWildcardQuery() {
        repository.NativeWildcardQuery("kafka").forEach(System.err::println);
    }

    @Test
    void stringQuery() {
        repository.StringQuery("kafka").forEach(System.err::println);
    }

    @Test
    void criteriaQuery() {
        repository.CriteriaQuery("elastic").forEach(System.err::println);
    }

    @Test
    void stringQueryByAuthor() {
        repository.StringQueryByAuthor("spring").forEach(System.err::println);
    }

    @Test
    @Disabled
    void deleteAll() {
        repository.deleteAll();
    }
}