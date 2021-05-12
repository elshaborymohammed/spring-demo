package com.demo.spring.data.repository;

import com.demo.spring.data.domain.OrderHistory;
import com.demo.spring.data.domain.Review;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryQueryBuilderImpl implements ReviewRepositoryQueryBuilder {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public SearchHits<OrderHistory> rating(Long receiver) {
        BoolQueryBuilder must = QueryBuilders.boolQuery()
                .must(QueryBuilders.nestedQuery(_PATH, QueryBuilders.matchQuery(_PATH + ".status", Review.ReviewStatus.ACCEPTED.name()), ScoreMode.None));

        Query query = new NativeSearchQueryBuilder()
                .withQuery(must)
                .build();

        return elasticsearchOperations.search(query, OrderHistory.class, IndexCoordinates.of(_INDEX));
    }
}
