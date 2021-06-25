package com.demo.spring.data.jpa.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Singular
    @OneToMany(mappedBy = "author")
    private List<Article> articles;
}
