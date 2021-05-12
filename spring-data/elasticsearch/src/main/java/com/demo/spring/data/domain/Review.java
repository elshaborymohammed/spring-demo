package com.demo.spring.data.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
public class Review {
    private Long id;
    private Long actionSubscriptionId;
    private Long actionUserId;
    private Long receiverSubscriptionId;
    private String rejectionReason;
    private String reviewDetails;
    private Rating rating;
    @Field(type = FieldType.Text)
    private ReviewStatus status;

    public enum ReviewStatus {
        ACCEPTED, PENDING, REJECTED;
    }
}
