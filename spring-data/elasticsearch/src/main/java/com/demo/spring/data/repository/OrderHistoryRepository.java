package com.demo.spring.data.repository;

import com.demo.spring.data.domain.OrderHistory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends ElasticsearchRepository<OrderHistory, String>, ReviewRepositoryQueryBuilder {

    List<OrderHistory> findByReviewsActionSubscriptionId(Long subscriptionId);

    List<OrderHistory> findByReviewsReceiverSubscriptionId(Long subscriptionId);
}
