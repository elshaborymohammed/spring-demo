package com.demo.spring.web.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@Document(indexName = "blogs", indexStoreType = "Article")
public class Post {
    @Id
    private String id;
    private String content;
    private String manufacturer;
}