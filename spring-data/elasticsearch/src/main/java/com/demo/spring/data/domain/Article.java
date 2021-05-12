package com.demo.spring.data.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "article", indexStoreType = "Article", createIndex = false)
public class Article {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "content")
    private String content;
    @Field(type = FieldType.Nested, name = "references")
    private List<Reference> references;
    @Field(type = FieldType.Nested, name = "authors", includeInParent = true)
    private List<Author> authors;
    @Field(type = FieldType.Object, name = "publishedBy")
    private Publisher publishedBy;
    @Field(type = FieldType.Keyword)
    private Set<String> tags;
    @CreatedDate
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time, name = "createdAt")
    private Instant createdAt;
}
