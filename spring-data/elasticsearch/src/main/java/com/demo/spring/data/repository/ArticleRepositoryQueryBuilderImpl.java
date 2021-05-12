package com.demo.spring.data.repository;

import com.demo.spring.data.domain.Article;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryQueryBuilderImpl implements ArticleRepositoryQueryBuilder {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public boolean createIndex() {
        return elasticsearchOperations.indexOps(Article.class).create();
    }

    @Override
    public SearchHits<Article> NativeQuery(String search) {
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(search, "content").fuzziness(Fuzziness.AUTO);
        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return elasticsearchOperations.search(query, Article.class, IndexCoordinates.of(_INDEX));
    }

    @Override
    public SearchHits<Article> NativeWildcardQuery(String search) {
        BoolQueryBuilder must = QueryBuilders.boolQuery()
                .must(QueryBuilders.wildcardQuery("content", "*" + search + "*"))
                .must(QueryBuilders.nestedQuery("authors", QueryBuilders.matchQuery("authors.name", "Bob"), ScoreMode.Total));

        Query query = new NativeSearchQueryBuilder()
                .withFilter(must)
//                .withQuery(matchQuery("title", articleTitle).minimumShouldMatch("75%"))
//                .withFilter(QueryBuilders.regexpQuery("content", ".*data.*"))
//                .withPageable(PageRequest.of(0, 10))
                .build();

        return elasticsearchOperations.search(query, Article.class, IndexCoordinates.of(_INDEX));
    }

    @Override
    public SearchHits<Article> StringQuery(String search) {
        Query query = new StringQuery("{\"match\":{\"content\":{\"query\":\"" + search + "\"}}}\"");
        return elasticsearchOperations.search(
                query,
                Article.class,
                IndexCoordinates.of(_INDEX));
    }

    @Override
    public SearchHits<Article> StringQueryByAuthor(String search) {
        Query searchQuery = new StringQuery("{\"match\":{\"authors.name\":{\"query\":\"" + search + "\"}}}\"");
        return elasticsearchOperations.search(
                searchQuery,
                Article.class,
                IndexCoordinates.of(_INDEX));
    }

    @Override
    public SearchHits<Article> CriteriaQuery(String search) {
        Criteria criteria = new Criteria("content")
                .startsWith(search);

        Query query = new CriteriaQuery(criteria);
        return elasticsearchOperations.search(
                query,
                Article.class,
                IndexCoordinates.of(_INDEX));
    }
}
