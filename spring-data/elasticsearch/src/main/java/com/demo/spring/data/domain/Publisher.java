package com.demo.spring.data.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@Document(indexName = "publisher", createIndex = false)
public class Publisher {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
}
