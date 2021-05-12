package com.demo.spring.data.repository;

import com.demo.spring.data.domain.OrderHistory;
import com.demo.spring.data.domain.Review;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

@TestMethodOrder(MethodOrderer.MethodName.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderHistoryRepositoryTest {

    @Autowired
    OrderHistoryRepository repository;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Test
    @Disabled
    void setup() {
        elasticsearchOperations.indexOps(OrderHistory.class).create();
    }

    @Test
    @Disabled
    void init() {
        Review pending = Review.builder().id(1L)
                .actionSubscriptionId(101L)
                .actionUserId(111L)
                .receiverSubscriptionId(121L)
                .status(Review.ReviewStatus.PENDING).reviewDetails("Good").build();

        Review rejected = Review.builder().id(2L)
                .actionSubscriptionId(101L)
                .actionUserId(111L)
                .receiverSubscriptionId(121L)
                .status(Review.ReviewStatus.REJECTED).reviewDetails("Fuck You...").build();

        Review accepted_1 = Review.builder().id(3L)
                .actionSubscriptionId(101L)
                .actionUserId(111L)
                .receiverSubscriptionId(121L)
                .status(Review.ReviewStatus.ACCEPTED).reviewDetails("Good 1").build();

        Review accepted_2 = Review.builder().id(4L)
                .actionSubscriptionId(102L)
                .actionUserId(112L)
                .receiverSubscriptionId(121L)
                .status(Review.ReviewStatus.ACCEPTED).reviewDetails("Good 2").build();

        Review accepted_3 = Review.builder().id(5L)
                .actionSubscriptionId(102L)
                .actionUserId(112L)
                .receiverSubscriptionId(122L)
                .status(Review.ReviewStatus.ACCEPTED).reviewDetails("Good 2").build();

        List.of(
                OrderHistory.builder()
                        .id(1L)
                        .reason("Reason")
                        .review(pending)
                        .review(accepted_1)
                        .build(),
                OrderHistory.builder()
                        .id(2L)
                        .reason("Reason")
                        .review(rejected)
                        .review(accepted_2)
                        .review(accepted_3)
                        .build()
        ).forEach(repository::save);
    }

    @Test
    void count() {
        System.err.println(repository.count());
    }

    @Test
    void findAll() {
        repository.findAll().forEach(System.err::println);
    }

    @Test
    void findByReviewsActionSubscriptionId() {
        repository.findByReviewsActionSubscriptionId(101L)
                .stream().map(OrderHistory::getReviews)
                .flatMap(Collection::stream)
                .forEach(System.err::println);
    }

    @Test
    void findByReviewsReceiverSubscriptionId() {
        repository.findByReviewsReceiverSubscriptionId(121L).forEach(System.err::println);
    }

    @Test
    void rating() {
        SearchHits<OrderHistory> hits = repository.rating(101L);
        System.out.println(hits);
        System.out.println(hits.getAggregations());
        System.out.println(hits.getTotalHits());
        hits.forEach(System.out::println);
    }

    @Test
    @Disabled
    void tearDown() {
        repository.deleteAll();
    }
}