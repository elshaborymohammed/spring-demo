package com.demo.spring.data.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Document(indexName = "post", createIndex = false)
public class Post {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "content")
    private String content;
    @Field(type = FieldType.Nested, name = "references")
    private List<Reference> references;
    @Field(type = FieldType.Nested, name = "authors")
    private Set<Author> authors;
    @Field(type = FieldType.Nested, name = "publishedBy", includeInParent = true)
    private Publisher publishedBy;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", name = "createdAt")
    private Date createdAt;
    @Field(type = FieldType.Keyword, name = "manufacturer")
    private String manufacturer;
}
