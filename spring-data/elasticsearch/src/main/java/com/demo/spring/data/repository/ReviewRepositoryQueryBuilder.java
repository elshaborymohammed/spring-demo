package com.demo.spring.data.repository;

import com.demo.spring.data.domain.OrderHistory;
import org.springframework.data.elasticsearch.core.SearchHits;

public interface ReviewRepositoryQueryBuilder {
    String _INDEX = "order_history";
    String _PATH = "reviews";

    SearchHits<OrderHistory> rating(Long receiver);
}
