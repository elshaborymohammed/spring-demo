package com.demo.spring.data.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "author", createIndex = false)
public class Author {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    @Field(type = FieldType.Text, name = "name")
    private String name;
}
