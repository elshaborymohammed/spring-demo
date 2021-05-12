package com.demo.spring.data.repository;

import com.demo.spring.data.domain.Article;
import org.springframework.data.elasticsearch.core.SearchHits;

public interface ArticleRepositoryQueryBuilder {
    String _INDEX = "article";

    boolean createIndex();

    SearchHits<Article> NativeQuery(String search);

    SearchHits<Article> NativeWildcardQuery(String search);

    SearchHits<Article> StringQuery(String search);

    SearchHits<Article> StringQueryByAuthor(String search);

    SearchHits<Article> CriteriaQuery(String search);
}
