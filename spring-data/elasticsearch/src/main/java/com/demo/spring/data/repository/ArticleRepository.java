package com.demo.spring.data.repository;

import com.demo.spring.data.domain.Article;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String>, ArticleRepositoryQueryBuilder {

    @Override
    List<Article> findAll();

    List<Article> findByContent(String content);

    List<Article> findByContentContaining(String content);

    @Query("{\"match\": {\"content\": {\"query\": \"?0\"}}}")
    List<Article> findByContentLike(String name);

    List<Article> findByTags(String tag);

    List<Article> findByTagsContaining(String tag);

    List<Article> findByPublishedByName(String name);

    List<Article> findByPublishedByNameContaining(String name);

    List<Article> findByReferencesName(String name);

    List<Article> findByReferencesNameContaining(String name);

    List<Article> findByReferencesContaining(String name);

    List<Article> findByAuthorsName(String author);

    List<Article> findByAuthorsNameContaining(String authorName);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    List<Article> findByAuthorsNameUsingCustomQuery(String name);

    @Query("{\"bool\": {\"must\": {\"match\": {\"references.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")
    List<Article> findByAuthorsNameAndFilteredTagQuery(String name, String tag);
}
