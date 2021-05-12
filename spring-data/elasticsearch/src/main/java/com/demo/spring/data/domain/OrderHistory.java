package com.demo.spring.data.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Builder
@Document(indexName = "order_history", createIndex = false)
public class OrderHistory {
    @Id
    private Long id;
    private String reason;
    @Field(type = FieldType.Nested)
    @Singular
    private List<Review> reviews;
}
