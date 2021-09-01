package com.demo.spring.data.jpa.repository;

import com.demo.spring.data.jpa.domain.Publisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>, Specification<Publisher> {
}
