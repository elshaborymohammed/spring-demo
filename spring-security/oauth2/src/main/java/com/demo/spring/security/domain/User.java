package com.demo.spring.security.domain;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user")
public class User {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ToString.Include
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @ToString.Include
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Role> roles = new LinkedHashSet<>();
}